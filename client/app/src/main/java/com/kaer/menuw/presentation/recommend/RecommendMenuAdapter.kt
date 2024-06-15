package com.kaer.menuw.presentation.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemRecommendMenuBinding
import com.kaer.menuw.domain.entity.RecommendMenu
import com.kaer.menuw.util.ItemDiffCallback
import com.kaer.menuw.util.base.BindingAdapter.setImage

class RecommendMenuAdapter :
    ListAdapter<RecommendMenu, RecommendMenuAdapter.RecommendMenuViewHolder>(
        ItemDiffCallback<RecommendMenu>(
            onContentsTheSame = {old, new -> old == new},
            onItemsTheSame =  {old, new -> old.menuId == new.menuId}
        )
    ) {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: RecommendMenu, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class RecommendMenuViewHolder(
        private val binding: ItemRecommendMenuBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecommendMenu) {
            binding.item = data
            binding.ivItemRecommendMenu.setImage(data.menuImgUrl)

            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendMenuViewHolder {
        val binding = ItemRecommendMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendMenuViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}