package umc.hackathon.presentation

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import umc.hackathon.R
import umc.hackathon.databinding.ActivityMainBinding
import umc.hackathon.presentation.base.BaseActivity
import umc.hackathon.presentation.create.CreateViewModel
import umc.hackathon.presentation.home.HomeViewModel
import umc.hackathon.presentation.mapview.MapViewViewModel
import umc.hackathon.util.EditTextUtil.setOnEditorActionListener
import umc.hackathon.util.repeatOnStarted

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController
    private val mapViewModel: MapViewViewModel by viewModels()
    private val createViewModel: CreateViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    override fun initView() {
        binding.mapViewModel = mapViewModel
        initNavigator()
        enableEdgeToEdge()
        /*binding.searchAddressEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
            mapViewModel.fetchGeocoding(binding.searchAddressEt.text.toString())
        }*/
    }

    override fun initObserver() {
        repeatOnStarted {
            mapViewModel.isInMapView.collect{
                Log.d("isInMapView", it.toString())
            }
        }
    }

    private fun initNavigator() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.mainBnv.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mapViewFragment){
                mapViewModel.setIsInMapView(true)
            } else {
                mapViewModel.setIsInMapView(false)
            }
        }
    }
}