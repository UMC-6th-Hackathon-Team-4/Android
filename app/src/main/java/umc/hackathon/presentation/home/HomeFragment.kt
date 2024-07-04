package umc.hackathon.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import umc.hackathon.R
import umc.hackathon.databinding.FragmentHomeBinding
import umc.hackathon.domain.model.TreasurePreview
import umc.hackathon.presentation.base.BaseFragment
import umc.hackathon.presentation.home.adapter.ImageTreasureRVA

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), ImageTreasureRVA.TreasurePreviewItemClickListener {

    private val imageTreasureRVA by lazy {
        ImageTreasureRVA()
    }
    private val viewModel: HomeViewModel by activityViewModels()
    override fun initObserver() {

    }

    override fun initView() {
        // Sample data
        initRVA()

    }

    private fun initRVA(){
        val treasures = listOf(
            TreasurePreview(0, "경주 여행", "dsd", "lock"),
            TreasurePreview(0, "나주 여행", "dsd", "lock"),
            TreasurePreview(0, "원주 여행", "dsd", "lock"),
            TreasurePreview(0, "경주 여행", "dsd", "lock"),
        )
        imageTreasureRVA.setTreasurePreviewItemClickListener(this)
        binding.recyclerView.adapter = imageTreasureRVA
        imageTreasureRVA.submitList(treasures)
    }

    override fun onClick(id: Int) {
        viewModel.setSelectedId(id)
        val dialog = PreviewTreasureDialogFragment()
        dialog.show(childFragmentManager, dialog.tag)
    }
}
