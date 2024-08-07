package umc.hackathon.presentation.create

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import umc.hackathon.R
import umc.hackathon.databinding.FragmentCreateSelectImageBinding
import umc.hackathon.presentation.base.BaseFragment

@AndroidEntryPoint
class CreateSelectImageFragment :
    BaseFragment<FragmentCreateSelectImageBinding>(R.layout.fragment_create_select_image) {

    //private lateinit var photoUri: Uri
    private val args: CreateSelectImageFragmentArgs by navArgs()
    private val navigator by lazy {
        findNavController()
    }
    private val viewModel: CreateViewModel by activityViewModels()

    override fun initObserver() {

    }

    override fun initView() {
        binding.img.setImageURI(args.uri)

        binding.txSave.setOnClickListener {
            if (!binding.editTextText.text.toString().isEmpty()){
                viewModel.setIsWritten(true)
            } else {
                viewModel.setIsWritten(false)
            }
            navigator.navigateUp()
            /*val content = binding.editTextText.text.toString()


            val result = Bundle().apply {
                putParcelable("photo_uri", photoUri)
                if (content.isNotEmpty()) {
                    putString("content", content)
                }
            }
            setFragmentResult("photo_detail", result)*/
        }
        binding.btnBack.setOnClickListener {
            navigator.navigateUp()
        }
        //parentFragmentManager.popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance(uri: Uri) =
            CreateSelectImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("photo_uri", uri)
                }
            }
    }

}