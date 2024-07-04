package umc.hackathon.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import umc.hackathon.R
import umc.hackathon.databinding.ItemTreasureBinding
import umc.hackathon.domain.model.TreasurePreview
class ImageTreasureRVA : ListAdapter<TreasurePreview, ImageTreasureRVA.TreasurePreviewViewHolder>(DiffCallback()) {

    private var treasurePreviewItemClickListener: TreasurePreviewItemClickListener? = null

    fun setTreasurePreviewItemClickListener(listener: TreasurePreviewItemClickListener){
        treasurePreviewItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreasurePreviewViewHolder {
        return TreasurePreviewViewHolder(
            ItemTreasureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TreasurePreviewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class TreasurePreviewViewHolder(private val binding: ItemTreasureBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(treasurePreview: TreasurePreview){
                binding.model = treasurePreview
                binding.root.setOnClickListener {
                    treasurePreviewItemClickListener?.onClick(treasurePreview.id)
                }
            }
        }

    class DiffCallback : DiffUtil.ItemCallback<TreasurePreview>() {
        override fun areItemsTheSame(oldItem: TreasurePreview, newItem: TreasurePreview) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TreasurePreview, newItem: TreasurePreview) =
            oldItem == newItem
    }

    interface TreasurePreviewItemClickListener{
        fun onClick(id: Int)
    }
}