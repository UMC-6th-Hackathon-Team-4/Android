package umc.hackathon.presentation.create

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import umc.hackathon.R
import umc.hackathon.databinding.FragmentCreateBinding
import umc.hackathon.presentation.base.BaseFragment

class CreateFragment : BaseFragment<FragmentCreateBinding>(R.layout.fragment_create) {

    private val args: CreateSelectImageFragmentArgs by navArgs()
    private val navigator by lazy {
        findNavController()
    }
    private val viewModel: CreateViewModel by activityViewModels()

    override fun initObserver() {

    }

    override fun initView() {

        val strEt1: String = binding.et1.getText().toString()
        val strEt2: String = binding.et2.getText().toString()
        val strEt3: String = binding.et3.getText().toString()
        val strEt4: String = binding.et4.getText().toString()

        binding.btnNext.setOnClickListener {
            val action = CreateFragmentDirections.actionCreateFragmentToCreateSelectAddFragment(
                tresId = 1 // api 결과 id
            )

            // Navigate to the next fragment
            findNavController().navigate(action)
        }
    }
}