package umc.hackathon.presentation.create

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import umc.hackathon.R
import umc.hackathon.databinding.DialogCreateFirstBinding
import umc.hackathon.databinding.DialogCreateSecondBinding
import umc.hackathon.presentation.base.BaseDialogFragment

class CreateSecondDialog : BaseDialogFragment<DialogCreateSecondBinding>(R.layout.dialog_create_second) {

    private val navigator by lazy {
        findNavController()
    }
    override fun initObserver() {

    }

    override fun initView() {
        binding.btnCreateSave.setOnClickListener {
            // Navigate to homeFragment
            dismiss()

        }
    }

}