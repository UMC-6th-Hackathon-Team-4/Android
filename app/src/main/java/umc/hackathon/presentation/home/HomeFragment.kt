package umc.hackathon.presentation.home

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import umc.hackathon.R
import umc.hackathon.databinding.FragmentHomeBinding
import umc.hackathon.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val navigator by lazy {
        findNavController()
    }
    override fun initObserver() {

    }

    override fun initView() {
        binding.tempBtn.setOnClickListener{
            navigator.navigate(R.id.action_homeFragment_to_createTreasureFragment)
        }
    }
}