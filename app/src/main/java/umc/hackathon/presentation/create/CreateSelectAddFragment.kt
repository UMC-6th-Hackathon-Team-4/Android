package umc.hackathon.presentation.create

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import umc.hackathon.R
import umc.hackathon.databinding.FragmentCreateSelectAddBinding
import umc.hackathon.databinding.FragmentCreateSelectImageBinding
import umc.hackathon.domain.model.Treasure
import umc.hackathon.presentation.base.BaseFragment
import umc.hackathon.util.repeatOnStarted

@AndroidEntryPoint
class CreateSelectAddFragment :
    BaseFragment<FragmentCreateSelectAddBinding>(R.layout.fragment_create_select_add) {

    private val viewModel: CreateSelectImageViewModel by activityViewModels()
    private var currentLastIndex = 0
    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        val currentSize = viewModel.selectedPhotos.value?.size ?: 0
        if (currentSize + uris.size <= 10) {
            val treasureList = mutableListOf<Treasure>()
            uris.forEach{
                treasureList.add(Treasure(currentLastIndex++, it, ""))
            }
            viewModel.addSelectedPhotos(treasureList)
            viewModel.initSelectedPhotos()
        } else {
            Toast.makeText(requireContext(), "10개까지만 업로드 가능합니다", Toast.LENGTH_SHORT).show()
        }
    }
    private val navigator by lazy {
        findNavController()
    }

    private lateinit var photoRVA: PhotoAdapter


    override fun initObserver() {
        repeatOnStarted {
            viewModel.selectedPhotosShared.collect{
                photoRVA.submitList(it)
            }
        }

        repeatOnStarted {
            viewModel.currentLastIndex.collect{
                if(it != 0){
                    currentLastIndex = it
                }
            }
        }
    }

    override fun initView() {
        photoRVA = PhotoAdapter(
            onPhotoClick = {
                photoPickerLauncher.launch("image/*")
            },
            onDeleteClick = { uri ->
                viewModel.removePhoto(uri)
            },
            onPhotoDetailClick = { uri, int ->
                viewModel.setSelectedIndex(int)
                val action = CreateTreasureFragmentDirections.actionCreateTreasureFragmentToCreateSelectImageFragment(uri, int)
                navigator.navigate(action)
            }
        )
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.itemAnimator = null
        binding.recyclerView.adapter = photoRVA

        viewModel.initSelectedPhotos()

        binding.btnBack.setOnClickListener {
            navigator.navigateUp()
        }

        binding.btnSave.setOnClickListener {
            //다이얼로그 띄우기
            CreateFirstDialog().show(parentFragmentManager, "FirstDialog")
        }
    }



    override fun onResume() {
        super.onResume()
        viewModel.initSelectedPhotos()
    }
}