package umc.hackathon.presentation.mapview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.overlay.LocationOverlay
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import umc.hackathon.R
import umc.hackathon.databinding.FragmentMapViewBinding
import umc.hackathon.presentation.base.BaseFragment
import umc.hackathon.util.EditTextUtil.setOnEditorActionListener
import umc.hackathon.util.repeatOnStarted

@AndroidEntryPoint
class MapViewFragment : BaseFragment<FragmentMapViewBinding>(R.layout.fragment_map_view) {
    private var naverMap: NaverMap? = null
    private lateinit var locationSource: FusedLocationSource
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            naverMap?.locationTrackingMode = LocationTrackingMode.Follow
        } else {
            // 권한이 거부된 경우 처리 (예: 사용자에게 권한이 필요하다고 알리기)
        }
    }

    private val viewModel: MapViewViewModel by activityViewModels()
    override fun initObserver() {
        repeatOnStarted {
            viewModel.latLng.collect{
                naverMap?.moveCamera(CameraUpdate.scrollTo(it))
            }
        }
    }

    override fun initView() {
        binding.viewModel = viewModel
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        checkAndRequestLocationPermissions()
        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        initMap()
        binding.searchAddressEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
            viewModel.fetchGeocoding(binding.searchAddressEt.text.toString())
        }
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    naverMap?.moveCamera(CameraUpdate.scrollTo(latLng))
                    naverMap?.locationOverlay?.position = latLng
                } else {
                }
            }
            .addOnFailureListener { exception ->
                Log.d("getLastKnownLocation", exception.message.toString())
            }
    }

    private fun checkLocationPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun checkAndRequestLocationPermissions() {
        if (checkLocationPermission()) {
            // 권한이 이미 있는 경우 추가적인 작업 필요 시 여기에 작성
            naverMap?.locationTrackingMode = LocationTrackingMode.Follow
        } else {
            requestLocationPermission()
        }
    }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance(NaverMapOptions().locationButtonEnabled(false)).also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync { map ->
            naverMap = map
            naverMap?.locationSource = locationSource
        }
    }

    override fun onResume() {
        super.onResume()
        if(naverMap != null){
            naverMap?.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    companion object {
        val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}

