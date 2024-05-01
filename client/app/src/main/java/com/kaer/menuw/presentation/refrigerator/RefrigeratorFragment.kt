package com.kaer.menuw.presentation.refrigerator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientBottomSheet
import com.kaer.menuw.presentation.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.refrigerator.add.SharedPreferenceManager
import com.kaer.menuw.util.base.BaseFragment

class RefrigeratorFragment :
    BaseFragment<FragmentRefrigeratorBinding>(R.layout.fragment_refrigerator) {

    private val viewModel by activityViewModels<AddIngredientViewModel>()
    private lateinit var sharedPreferences: SharedPreferenceManager

    private var _refrigeratorAdapter: RefrigeratorAdapter? = null
    private val refrigeratorAdapter
        get() = requireNotNull(_refrigeratorAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        sharedPreferences = SharedPreferenceManager(requireContext())

        clickAddIngredientBtn()
        initSetRefrigerator()
    }

    private fun initSetRefrigerator() {
        _refrigeratorAdapter = RefrigeratorAdapter()
        binding.rcvRefrigeratorList.adapter = refrigeratorAdapter
        refrigeratorAdapter.submitList(sharedPreferences.getIngredientList())

        updateRefrigerator()
    }

    private fun updateRefrigerator() {
        viewModel.updateStoredIngredientArray.observe(viewLifecycleOwner) {
            refrigeratorAdapter.submitList(it)
        }
    }

    private fun clickSeeRecommendBtn() {
        // TODO 레시피 추천
    }

    private fun clickAddIngredientBtn() {

        binding.btnRefrigeratorAddIngredient.setOnClickListener {
            AddIngredientBottomSheet().show(parentFragmentManager, BOTTOM_SHEET)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _refrigeratorAdapter = null
    }

    companion object {
        private const val BOTTOM_SHEET = "BOTTOM_SHEET"
    }
}