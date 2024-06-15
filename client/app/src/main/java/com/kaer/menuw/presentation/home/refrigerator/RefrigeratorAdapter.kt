package com.kaer.menuw.presentation.home.refrigerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemIngredientRefrigeratorBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.domain.entity.RefrigeratorIngredientItem
import com.kaer.menuw.util.ItemDiffCallback
import com.kaer.menuw.util.base.BindingAdapter.setCoilImage
import timber.log.Timber
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class RefrigeratorAdapter :
    ListAdapter<RefrigeratorIngredientItem, RefrigeratorAdapter.RefrigeratorViewHolder>(
        ItemDiffCallback<RefrigeratorIngredientItem>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.ingredientId == new.ingredientId }
        )
    ) {

    private var onItemClickListener: ((RefrigeratorIngredientItem) -> Unit)? = null
    var editEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var selectedIngredientArray: ArrayList<RefrigeratorIngredientItem> = ArrayList()

    inner class RefrigeratorViewHolder(
        val binding: ItemIngredientRefrigeratorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RefrigeratorIngredientItem, onClickListener: View.OnClickListener) {
            val localDate = LocalDate.now()
            val expiryDateFormat = LocalDate.parse(data.expiryDate, DateTimeFormatter.ofPattern("yyyy - M - d"))
            with(binding) {
                item = data
                if (localDate.compareTo(expiryDateFormat) >= 0) {
                    Timber.d("날짜 출력 -> $localDate")
                    tvIngredientExpiryDate.text = data.expiryDate + " : " + ChronoUnit.DAYS.between(expiryDateFormat, localDate)
                } else {
                    tvIngredientExpiryDate.text = data.expiryDate
                }
                ivIngredientImg.setCoilImage(data.ingredientImageUrl)
                root.setOnClickListener(onClickListener)
            }
        }
    }

    fun setOnIngredientClickListener(listener: (RefrigeratorIngredientItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private fun ingredientSelection(
        binding: ItemIngredientRefrigeratorBinding,
        ingredientItem: RefrigeratorIngredientItem
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
        ingredientArray: ArrayList<RefrigeratorIngredientItem>,
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