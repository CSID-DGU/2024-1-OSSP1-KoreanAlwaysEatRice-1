package com.kaer.menuw.presentation.refrigerator.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemIngredientTypeBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.util.ItemDiffCallback

class IngredientTypeAdapter :
    ListAdapter<IngredientTotal, IngredientTypeAdapter.IngredientTypeViewHolder>(
        ItemDiffCallback<IngredientTotal>(
            onItemsTheSame = { oldItem, newItem -> oldItem.type == newItem.type },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    class IngredientTypeViewHolder(
        private val binding: ItemIngredientTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal) {
            binding.tvItemIngredientTypeName.text = data.type
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientTypeViewHolder {
        val binding = ItemIngredientTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IngredientTypeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IngredientTypeViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}