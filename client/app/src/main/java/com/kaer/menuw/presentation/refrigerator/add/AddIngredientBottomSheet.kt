package com.kaer.menuw.presentation.refrigerator.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kaer.menuw.databinding.BottomsheetIngredientBinding

class AddIngredientBottomSheet : BottomSheetDialogFragment() {

    private val viewModel by viewModels<AddIngredientViewModel>()

    private var _binding: BottomsheetIngredientBinding? = null
    val binding: BottomsheetIngredientBinding
        get() = requireNotNull(_binding)

    private var _ingredientTypeAdapter: IngredientTypeAdapter? = null
    private val ingredientTypeAdapter
        get() = requireNotNull(_ingredientTypeAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetIngredientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initMakeAdapter()
    }

    private fun initMakeAdapter() {
        _ingredientTypeAdapter = IngredientTypeAdapter()
        binding.rcvAddIngredientType.adapter = ingredientTypeAdapter
        viewModel.mockIngredientList.observe(this) {
            ingredientTypeAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}