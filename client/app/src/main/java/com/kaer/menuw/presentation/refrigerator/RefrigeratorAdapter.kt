package com.kaer.menuw.presentation.refrigerator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.databinding.ItemIngredientListBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.util.ItemDiffCallback
import timber.log.Timber

class RefrigeratorAdapter(selectedArray: ArrayList<IngredientTotal.IngredientItem>):
    ListAdapter<IngredientTotal.IngredientItem, RefrigeratorAdapter.RefrigeratorViewHolder>(
        ItemDiffCallback<IngredientTotal.IngredientItem> (
            onContentsTheSame = {old, new -> old == new},
            onItemsTheSame = {old, new -> old.ingredientId == new.ingredientId}
        )
    ) {

    val selectedArray = selectedArray

    inner class RefrigeratorViewHolder(
        private val binding: ItemIngredientListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal.IngredientItem) {
            Timber.d("테스트테스트 -> adapter : $data")
            binding.item = data
//            if (selectedArray.contains(data)) {
//                Timber.d("테스트테스트 -> adapter : $data")
//                binding.item = data
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefrigeratorViewHolder {
        val binding = ItemIngredientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RefrigeratorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RefrigeratorViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}