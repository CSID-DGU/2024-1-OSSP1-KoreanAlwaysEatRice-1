package com.kaer.menuw.presentation.refrigerator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientBottomSheet
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.refrigerator.add.SharedPreferenceManager
import com.kaer.menuw.util.base.BaseFragment

class RefrigeratorFragment :
    BaseFragment<FragmentRefrigeratorBinding>(R.layout.fragment_refrigerator) {

    private val viewModel by viewModels<AddIngredientViewModel>()
    private lateinit var sharedPreferences: SharedPreferenceManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        sharedPreferences = SharedPreferenceManager(requireContext())

        clickAddIngredientBtn()
    }

    private fun clickSeeRecommendBtn() {
        //
    }

    private fun clickAddIngredientBtn() {

        binding.btnRefrigeratorAddIngredient.setOnClickListener {
            AddIngredientBottomSheet().show(parentFragmentManager, BOTTOM_SHEET)
        }
    }

    companion object {
        private const val BOTTOM_SHEET = "BOTTOM_SHEET"
    }
}