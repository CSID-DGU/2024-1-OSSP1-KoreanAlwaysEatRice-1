package com.kaer.menuw.presentation.home.likemenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemLikeMenuBinding
import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.util.ItemDiffCallback
import com.kaer.menuw.util.base.BindingAdapter.setImage

class LikeMenuListAdapter: ListAdapter<LikeMenu, LikeMenuListAdapter.LikeMenuListViewHolder>(
    ItemDiffCallback<LikeMenu>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.menuId == new.menuId}
    )
) {

    inner class LikeMenuListViewHolder(
        private val binding: ItemLikeMenuBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: LikeMenu) {
            binding.item = data
            binding.ivItemLikeMenu.setImage(data.menuImageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeMenuListViewHolder {
        val binding = ItemLikeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LikeMenuListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikeMenuListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}