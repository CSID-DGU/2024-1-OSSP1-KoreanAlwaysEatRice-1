package com.kaer.menuw.presentation.home.refrigerator.add.checkdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kaer.menuw.databinding.BottomsheetGetExpiryDateBinding
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.home.refrigerator.add.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        val expiryDate = ArrayList<String>()
        var currentIndex = 0
        viewModel.selectedIngredientArray.observe(viewLifecycleOwner) { ingredients ->
            binding.btnExpiryDate.setOnClickListener {
                if (currentIndex < ingredients.size) {
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