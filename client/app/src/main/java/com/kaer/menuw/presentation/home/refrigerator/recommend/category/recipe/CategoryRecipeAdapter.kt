package com.kaer.menuw.presentation.home.refrigerator.recommend.category.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ItemCategoryRecipeBinding
import com.kaer.menuw.domain.entity.CategoryRecipe
import com.kaer.menuw.util.ItemDiffCallback

class CategoryRecipeAdapter :
    ListAdapter<CategoryRecipe, CategoryRecipeAdapter.CategoryRecipeViewHolder>(
        ItemDiffCallback<CategoryRecipe>(
            onItemsTheSame = { old, new -> old.recipeName == new.recipeName },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition: Int = 10

    interface OnItemClickListener {
        fun onItemClick(item: CategoryRecipe, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class CategoryRecipeViewHolder(
        private val binding: ItemCategoryRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryRecipe) {
            binding.item = data
            binding.ivItemRecipeImg.setImageResource(data.recipeImg)
//             binding.root.setOnClickListener {
//                 recipeSelection(binding, data.recipeName)
//                 onItemClickListener?.let { it(data.recipeName) }
//             }

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
        binding: ItemCategoryRecipeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.layoutItemCategory.setBackgroundResource(R.drawable.shape_bright_fill_5_green_line_rect)
            }

            false -> {
                binding.layoutItemCategory.setBackgroundResource(R.drawable.shape_green2_bright_fill_5_rect)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecipeViewHolder {
        val binding = ItemCategoryRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryRecipeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}