package com.kaer.menuw.presentation.refrigerator.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemIngredientListBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.util.ItemDiffCallback

class IngredientListAdapter(selectedArray: ArrayList<Int>) :
    ListAdapter<IngredientTotal.IngredientItem, IngredientListAdapter.IngredientListViewHolder>(
        ItemDiffCallback<IngredientTotal.IngredientItem>(
            onItemsTheSame = { oldItem, newItem -> oldItem.ingredientId == newItem.ingredientId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    private var onItemClickListener: ((IngredientTotal.IngredientItem) -> Unit)? = null
//    var selectedIngredientArray = arrayListOf<Int>()
    var selectedIngredientArray = selectedArray

    inner class IngredientListViewHolder(
        val binding: ItemIngredientListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal.IngredientItem, onClickListener: View.OnClickListener) {
            with (binding) {
                item = data
                root.isActivated = selectedIngredientArray.contains(data.ingredientId)
                root.setOnClickListener(onClickListener)
            }
        }
    }

    fun setOnIngredientClickListener(listener: (IngredientTotal.IngredientItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private fun ingredientSelection(
        binding: ItemIngredientListBinding,
        ingredientItem: IngredientTotal.IngredientItem
    ) {
        val isIngredientSelected: Boolean =
            selectedIngredientArray.contains(ingredientItem.ingredientId)

        if (isIngredientSelected) {
            removeIngredientItem(
                selectedIngredientArray,
                selectedIngredientArray.indexOf(ingredientItem.ingredientId),
                binding
            )
        } else {
            selectedIngredientArray.add(ingredientItem.ingredientId)
            binding.root.isActivated = true
        }
    }

    private fun removeIngredientItem(
        ingredientArray: ArrayList<Int>,
        selectedId: Int,
        binding: ItemIngredientListBinding
    ) {
        ingredientArray.removeAt(selectedId)
        binding.root.isActivated = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListViewHolder {
        val binding =
            ItemIngredientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientListViewHolder, position: Int) {
        holder.apply {
            onBind(
                currentList[position],
                View.OnClickListener {
                    ingredientSelection(binding, currentList[position])
                    onItemClickListener?.let { it(currentList[position]) }
                }
            )
        }
    }
}