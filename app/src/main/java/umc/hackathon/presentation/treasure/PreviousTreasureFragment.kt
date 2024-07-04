package umc.hackathon.presentation.treasure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import umc.hackathon.R


import umc.hackathon.databinding.FragmentPreviousTreasureBinding
import umc.hackathon.presentation.base.BaseFragment

class PreviousTreasureFragment: BaseFragment<FragmentPreviousTreasureBinding>(R.layout.fragment_previous_treasure) {
    private val data = mutableListOf<Item>()
    override fun initObserver() {

    }

    override fun initView() {
        var recyclerView = binding.recyclerView // recyclerView 초기화

        setdata()

        val adapter = ItemAdapter(requireContext(),data)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter // adapter 설정
    }

    fun setdata(){
        data.add(Item(false, "대전 빵지순례",8,157,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
        data.add(Item(true, "가평 빠지여행",11,248,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
        data.add(Item(false, "거창 캠핑",33,32,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
        data.add(Item(false, "속초 가족여행",3,747,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
        data.add(Item(false, "속초 대진즈",8,157,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
        data.add(Item(true, "대전 빵지순례",8,157,"물에서 노는 시간 2시간, 먹는 시간 5시간",R.drawable.rectangle_corner_10_white))
    }
}
