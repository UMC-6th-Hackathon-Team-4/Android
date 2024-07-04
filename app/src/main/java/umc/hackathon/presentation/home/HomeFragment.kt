package umc.hackathon.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import umc.hackathon.R
import umc.hackathon.databinding.FragmentHomeBinding
import umc.hackathon.presentation.base.BaseFragment
import umc.hackathon.presentation.home.adapter.ImageTreasureRVA

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initObserver() {

    }

    override fun initView() {
        // Sample data
        val treasures = listOf(
            ImageTreasureRVA.TreasureData(R.drawable.logo, "Description 1"),
            ImageTreasureRVA.TreasureData(R.drawable.logo, "Description 2"),
            ImageTreasureRVA.TreasureData(R.drawable.logo, "Description 3")
        )


        val adapter = ImageTreasureRVA(treasures) { treasure ->
            val dialog = PreviewTreasureDialogFragment()
            dialog.show(childFragmentManager, dialog.tag)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }
}
