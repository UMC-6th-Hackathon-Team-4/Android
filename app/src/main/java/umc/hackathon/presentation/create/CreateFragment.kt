package umc.hackathon.presentation.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import umc.hackathon.R
import umc.hackathon.databinding.FragmentCreateBinding
import umc.hackathon.presentation.base.BaseFragment
import java.io.File

@AndroidEntryPoint
class CreateFragment : BaseFragment<FragmentCreateBinding>(R.layout.fragment_create) {

    private val viewModel: CreateViewModel by viewModels()
    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        val currentSize = viewModel.selectedPhotos.value?.size ?: 0
        if (currentSize + uris.size <= 10) {
            viewModel.addSelectedPhotos(uris)
        } else {
            Toast.makeText(requireContext(), "10개까지만 업로드 가능합니다", Toast.LENGTH_SHORT).show()
        }
    }


    override fun initObserver() {
        viewModel.selectedPhotos.observe(viewLifecycleOwner, { photos ->
            (binding.recyclerView.adapter as PhotoAdapter).submitList(photos)
        })

        viewModel.uploadResponse.observe(viewLifecycleOwner, { response ->
            // Handle upload response
        })
    }

    override fun initView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = PhotoAdapter(
            onPhotoClick = {
                photoPickerLauncher.launch("image/*")
            },
            onDeleteClick = { uri ->
                viewModel.removePhoto(uri)
            },
            onPhotoDetailClick = { uri ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, CreateSelectImageFragment.newInstance(uri))
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.btnSave.setOnClickListener {
            val imageUris = viewModel.selectedPhotos.value ?: emptyList()

            val imageParts = imageUris.map { uri ->
                val file = File(uri.path)
                val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("images", file.name, requestFile)
            }
           // viewModel.uploadPost(title, content, imageParts)
        }
    }
}