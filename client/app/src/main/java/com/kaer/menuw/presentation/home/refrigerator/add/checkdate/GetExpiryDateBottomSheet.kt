package com.kaer.menuw.presentation.home.refrigerator.add.checkdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kaer.menuw.databinding.BottomsheetGetExpiryDateBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.home.refrigerator.add.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.exp

@AndroidEntryPoint
class GetExpiryDateBottomSheet : BottomSheetDialogFragment() {

    private val viewModel by activityViewModels<AddIngredientViewModel>()

    private lateinit var sharedPreferences: SharedPreferenceManager

    private var _binding: BottomsheetGetExpiryDateBinding? = null
    val binding: BottomsheetGetExpiryDateBinding
        get() = requireNotNull(_binding as BottomsheetGetExpiryDateBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetGetExpiryDateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = SharedPreferenceManager(requireContext())

        setExpiryDate()
    }

    private fun setExpiryDate() {
        viewModel.selectedIngredientArray.observe(viewLifecycleOwner) { ingredients ->
            val expiryDate = viewModel.getDatesFromRefrigerator(sharedPreferences.getIngredientList(), ingredients)
            var currentIndex = expiryDate.size
            if (currentIndex >= ingredients.size) {
                viewModel.setExpiryDate(expiryDate)
                dismiss()
            } else {
                binding.tvExpiryDateIngredient.text = ingredients[currentIndex].ingredientName
                binding.btnExpiryDate.setOnClickListener {
                    Timber.d("테스트 클릭 재료명 -> ${ingredients[currentIndex].ingredientName}")
                    val selectedDate =
                        "${binding.datepickerExpiryDate.year} - ${binding.datepickerExpiryDate.month + 1} - ${binding.datepickerExpiryDate.dayOfMonth}"
                    expiryDate.add(selectedDate)
                    Timber.d("테스트 클릭 expiry-> $expiryDate")

                    if (expiryDate.size == ingredients.size) {
                        viewModel.setExpiryDate(expiryDate)
                        dismiss()
                    }
                    currentIndex++
                }
            }
        }
    }
}