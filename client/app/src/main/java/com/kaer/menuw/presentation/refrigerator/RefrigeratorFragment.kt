package com.kaer.menuw.presentation.refrigerator

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientActivity
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
        val intent = Intent(requireActivity(), AddIngredientActivity::class.java)
        binding.btnRefrigeratorAddIngredient.setOnClickListener {
            startActivity(intent)
        }
    }
}