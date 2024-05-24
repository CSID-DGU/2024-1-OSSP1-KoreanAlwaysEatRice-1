package com.kaer.menuw.presentation.home.menurecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemRecipeOrderBinding
import com.kaer.menuw.domain.entity.RecipeOrder

class RecipePageAdapter: RecyclerView.Adapter<RecipePageAdapter.RecipePageViewHolder>() {

    val itemList: List<RecipeOrder> = listOf(
        RecipeOrder("", "1.~~~~한다"),
        RecipeOrder("", "2.~~~~한다"),
        RecipeOrder("", "3.~~~~한다"),
        RecipeOrder("", "4.~~~~한다"),
    )

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePageViewHolder {
        val binding: ItemRecipeOrderBinding =
            ItemRecipeOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipePageViewHolder, position: Int) {
        return holder.onBind(itemList[position])
    }

    inner class RecipePageViewHolder(
        private val binding: ItemRecipeOrderBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeOrder) {
            binding.tvItemRecipeOrderExplain.text = data.recipeListOrder
        }
    }
}