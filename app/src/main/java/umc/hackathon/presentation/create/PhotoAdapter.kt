package umc.hackathon.presentation.create

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import umc.hackathon.databinding.ItemPhotoBinding
import umc.hackathon.databinding.ItemPhotoPickerButtonBinding
import umc.hackathon.domain.model.Treasure

class PhotoAdapter(
    private val onPhotoClick: () -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onPhotoDetailClick: (Uri, Int) -> Unit
) : ListAdapter<Treasure, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    companion object {
        private const val TYPE_PICKER_BUTTON = 0
        private const val TYPE_PHOTO = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_PICKER_BUTTON else TYPE_PHOTO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_PICKER_BUTTON) {
            val binding = ItemPhotoPickerButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PhotoPickerButtonViewHolder(binding)
        } else {
            val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PhotoViewHolder(binding, onDeleteClick, onPhotoDetailClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            holder.bind(getItem(position - 1)) // Adjust for picker button
        } else if (holder is PhotoPickerButtonViewHolder) {
            holder.bind(onPhotoClick)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1 // Adjust for picker button
    }

    class PhotoPickerButtonViewHolder(private val binding: ItemPhotoPickerButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onPhotoClick: () -> Unit) {
            binding.root.setOnClickListener { onPhotoClick() }
        }
    }

    class PhotoViewHolder(
        private val binding: ItemPhotoBinding,
        private val onDeleteClick: (Int) -> Unit,
        private val onPhotoDetailClick: (Uri, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(treasure: Treasure) {
            binding.model = treasure
            binding.imageView.setImageURI(treasure.uri)
            binding.btnDelete.setOnClickListener {
                onDeleteClick(treasure.index)
            }
            binding.imageView.setOnClickListener {
                onPhotoDetailClick(treasure.uri, treasure.index)
            }
            if (binding.imgTxt != null) {
                binding.imgTxt.visibility = View.VISIBLE
            } else {
                binding.imgTxt.visibility = View.INVISIBLE
            }
        }
    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<Treasure>() {
        override fun areItemsTheSame(oldItem: Treasure, newItem: Treasure): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Treasure, newItem: Treasure): Boolean {
            return oldItem == newItem
        }
    }
}
