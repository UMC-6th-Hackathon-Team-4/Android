package umc.hackathon.presentation.home

import androidx.fragment.app.activityViewModels
import umc.hackathon.R
import umc.hackathon.databinding.DialogPreviewTreasureBinding
import umc.hackathon.presentation.base.BaseDialogFragment
import umc.hackathon.util.repeatOnStarted

class PreviewTreasureDialogFragment: BaseDialogFragment<DialogPreviewTreasureBinding>(R.layout.dialog_preview_treasure) {
    private val viewModel: HomeViewModel by activityViewModels()
    override fun initObserver() {
        repeatOnStarted {
            viewModel.selectedId.collect{

            }
        }
    }

    override fun initView() {

    }
}