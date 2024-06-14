package com.kaer.menuw.presentation.home.refrigerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemIngredientRefrigeratorBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.util.ItemDiffCallback
import com.kaer.menuw.util.base.BindingAdapter.setCoilImage

class RefrigeratorAdapter :
    ListAdapter<IngredientTotal.IngredientItem, RefrigeratorAdapter.RefrigeratorViewHolder>(
        ItemDiffCallback<IngredientTotal.IngredientItem>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.ingredientId == new.ingredientId }
        )
    ) {

    private var onItemClickListener: ((IngredientTotal.IngredientItem) -> Unit)? = null
    var editEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var selectedIngredientArray: ArrayList<IngredientTotal.IngredientItem> = ArrayList()

    inner class RefrigeratorViewHolder(
        val binding: ItemIngredientRefrigeratorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientTotal.IngredientItem, onClickListener: View.OnClickListener) {
            with(binding) {
                item = data
                ivIngredientImg.setCoilImage(data.ingredientImageUrl)
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
        binding: ItemIngredientRefrigeratorBinding,
        ingredientItem: IngredientTotal.IngredientItem
    ) {
        val isIngredientSelected: Boolean = selectedIngredientArray.contains(ingredientItem)

        if (isIngredientSelected) {
            removeIngredientItem(
                selectedIngredientArray,
                selectedIngredientArray.indexOf(ingredientItem),
                binding
            )
        } else {
            selectedIngredientArray.add(ingredientItem)
            binding.root.isActivated = true
        }
    }

    private fun removeIngredientItem(
        ingredientArray: ArrayList<IngredientTotal.IngredientItem>,
        selectedId: Int,
        binding: ItemIngredientRefrigeratorBinding
    ) {
        ingredientArray.removeAt(selectedId)
        binding.root.isActivated = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefrigeratorViewHolder {
        val binding =
            ItemIngredientRefrigeratorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RefrigeratorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RefrigeratorViewHolder, position: Int) {
        holder.apply {
            onBind(
                currentList[position],
                View.OnClickListener {
                    if (editEnabled.value == true) {
                        ingredientSelection(binding, currentList[position])
                        onItemClickListener?.let { it(currentList[position]) }
                    }
                }
            )
        }
    }
}