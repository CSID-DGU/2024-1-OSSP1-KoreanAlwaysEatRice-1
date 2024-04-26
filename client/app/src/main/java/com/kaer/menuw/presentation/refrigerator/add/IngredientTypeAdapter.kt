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

    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition: Int = 0

    interface OnItemClickListener {
        fun onItemClick(item: IngredientTotal, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class IngredientTypeViewHolder(
        private val binding: ItemIngredientTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal) {
            binding.tvItemIngredientTypeName.text = data.type

            if (onItemClickListener != null) {
                binding.root.setOnClickListener {
                    onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
                        selectedPosition = absoluteAdapterPosition
                    }
                }
            }
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