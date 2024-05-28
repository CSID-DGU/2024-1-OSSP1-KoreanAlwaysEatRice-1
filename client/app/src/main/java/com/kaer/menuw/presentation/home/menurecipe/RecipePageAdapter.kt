package com.kaer.menuw.presentation.home.menurecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemRecipeOrderBinding
import com.kaer.menuw.domain.entity.RecipeListItem
import com.kaer.menuw.util.ItemDiffCallback
import com.kaer.menuw.util.base.BindingAdapter.setImage

class RecipePageAdapter: ListAdapter<RecipeListItem, RecipePageAdapter.RecipePageViewHolder>(
    ItemDiffCallback<RecipeListItem>(
        onItemsTheSame = { oldItem, newItem -> oldItem.recipeOrder == newItem.recipeOrder },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    inner class RecipePageViewHolder(
        private val binding: ItemRecipeOrderBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeListItem) {
            binding.tvItemRecipeOrderExplain.text = data.recipeOrder
            binding.ivItemRecipeOrderImg.setImage(data.recipeImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePageViewHolder {
        val binding: ItemRecipeOrderBinding =
            ItemRecipeOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipePageViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }
}