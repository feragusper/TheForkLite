package com.feragusper.theforklite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feragusper.theforklite.databinding.ListItemImageBinding

class ImageGalleryAdapter : ListAdapter<String, ImageGalleryAdapter.ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ListItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageURL = getItem(position)
        if (imageURL != null) {
            holder.bind(imageURL)
        }
    }

    class ImageViewHolder(private val binding: ListItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.apply {
                imageUrl = url
                executePendingBindings()
            }
        }
    }

    class ImageDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}