package umc.hackathon.presentation.treasure

import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.widget.ZoomControlView
import dagger.hilt.android.AndroidEntryPoint
import umc.hackathon.R
import umc.hackathon.databinding.FragmentPreviousTreasureBinding
import umc.hackathon.presentation.base.BaseFragment

@AndroidEntryPoint
class PreviousTreasureFragment : BaseFragment<FragmentPreviousTreasureBinding>(R.layout.fragment_previous_treasure),
    OnMapReadyCallback {
    private var naverMap: NaverMap? = null
    override fun initObserver() {

    }

    override fun initView() {
        initMap()
    }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance(NaverMapOptions().locationButtonEnabled(false)).also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync { map ->
            naverMap = map
        }
    }

    override fun onMapReady(p0: NaverMap) {

    }


}