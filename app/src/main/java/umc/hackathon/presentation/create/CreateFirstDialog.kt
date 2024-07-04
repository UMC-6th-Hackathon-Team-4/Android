package umc.hackathon.presentation.create

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import umc.hackathon.R
import umc.hackathon.databinding.DialogCreateFirstBinding
import umc.hackathon.databinding.FragmentSettingBinding
import umc.hackathon.presentation.base.BaseDialogFragment

class CreateFirstDialog : BaseDialogFragment<DialogCreateFirstBinding>(R.layout.dialog_create_first) {

    override fun initObserver() {

    }

    override fun initView() {
        binding.imageView5.setOnClickListener {
            // Show the second dialog
            dismiss()
            CreateSecondDialog().show(parentFragmentManager, "SecondDialog")
        }
    }

}
