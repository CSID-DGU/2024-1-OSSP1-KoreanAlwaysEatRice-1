package com.kaer.menuw.presentation.home.refrigerator.recommend.category.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ItemCategoryTypeBinding
import com.kaer.menuw.util.ItemDiffCallback

class CategoryTypeAdapter :
    ListAdapter<String, CategoryTypeAdapter.CategoryTypeViewHolder>(
        ItemDiffCallback<String>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition: Int = 10

    interface OnItemClickListener {
        fun onItemClick(item: String, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class CategoryTypeViewHolder(
        private val binding: ItemCategoryTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.tvItemCategoryType.text = data

            if (selectedPosition == absoluteAdapterPosition) {
                changeItemColor(binding, true)
            } else {
                changeItemColor(binding, false)
            }

            if (onItemClickListener != null) {
                binding.root.setOnClickListener {
                    if (selectedPosition != absoluteAdapterPosition) {
                        changeItemColor(binding, true)
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition
                    } else {
                        changeItemColor(binding, false)
                        notifyItemChanged(selectedPosition)
                        selectedPosition = 10
                    }
                    onItemClickListener?.onItemClick(data, selectedPosition)
                }
            }
        }
    }

    private fun changeItemColor(
        binding: ItemCategoryTypeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.layoutItemCategoryType.setBackgroundResource(R.drawable.shape_bright_fill_3_green_line_rect)
            }

            false -> {
                binding.layoutItemCategoryType.setBackgroundResource(R.drawable.shape_gray_bright_fill_3_rect)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTypeViewHolder {
        val binding = ItemCategoryTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryTypeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}