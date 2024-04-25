package com.kaer.menuw.presentation.refrigerator

import android.os.Bundle
import android.view.View
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientBottomSheet
import com.kaer.menuw.util.base.BaseFragment

class RefrigeratorFragment: BaseFragment<FragmentRefrigeratorBinding>(R.layout.fragment_refrigerator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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