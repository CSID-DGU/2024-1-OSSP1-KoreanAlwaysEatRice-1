package com.kaer.menuw.presentation.refrigerator.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemIngredientListBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.util.ItemDiffCallback
import timber.log.Timber

class IngredientListAdapter:
    ListAdapter<IngredientTotal.IngredientItem, IngredientListAdapter.IngredientListViewHolder>(
        ItemDiffCallback<IngredientTotal.IngredientItem>(
            onItemsTheSame = { oldItem, newItem -> oldItem.ingredientId == newItem.ingredientId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    class IngredientListViewHolder(
        private val binding: ItemIngredientListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal.IngredientItem) {
            binding.tvIngredientName.text = data.ingredientName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListViewHolder {
        val binding =
            ItemIngredientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}