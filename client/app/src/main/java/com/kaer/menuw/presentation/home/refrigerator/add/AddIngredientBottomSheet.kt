package com.kaer.menuw.presentation.home.refrigerator.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kaer.menuw.databinding.BottomsheetIngredientBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.DAIRY_FOOD
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.FISH
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.GRAIN
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.MEAT
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.SEASONING
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.VEGETABLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIngredientBottomSheet : BottomSheetDialogFragment() {

    private val viewModel by activityViewModels<AddIngredientViewModel>()

    private lateinit var sharedPreferences: SharedPreferenceManager

    private var _binding: BottomsheetIngredientBinding? = null
    val binding: BottomsheetIngredientBinding
        get() = requireNotNull(_binding as BottomsheetIngredientBinding)

    private var _ingredientTypeAdapter: IngredientTypeAdapter? = null
    private val ingredientTypeAdapter
        get() = requireNotNull(_ingredientTypeAdapter)

    private var _ingredientListAdapter: IngredientListAdapter? = null
    private val ingredientListAdapter
        get() = requireNotNull(_ingredientListAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetIngredientBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        sharedPreferences = SharedPreferenceManager(requireContext())

        initSetAdapter()
    }

    private fun initSetAdapter() {
        viewModel.getIngredientList()
        setTypeAdapter()
        changeListAdapter()
    }

    private fun setTypeAdapter() {
        _ingredientTypeAdapter = IngredientTypeAdapter().apply {
            setOnItemClickListener(object : IngredientTypeAdapter.OnItemClickListener {
                override fun onItemClick(item: IngredientTotal, position: Int) {
                    when (item.type) {
                        VEGETABLE -> viewModel.clickTypeId(0)
                        DAIRY_FOOD -> viewModel.clickTypeId(1)
                        GRAIN -> viewModel.clickTypeId(2)
                        MEAT -> viewModel.clickTypeId(3)
                        FISH -> viewModel.clickTypeId(4)
                        SEASONING -> viewModel.clickTypeId(5)
                        else -> viewModel.clickTypeId(0)
                    }
                }
            })
        }

        binding.rcvAddIngredientType.adapter = ingredientTypeAdapter
        viewModel.ingredientList.observe(viewLifecycleOwner) {
            ingredientTypeAdapter.submitList(it)
            ingredientListAdapter.submitList(it[0].ingredientListItem)
        }
        makeListAdapter()
    }

    private fun makeListAdapter() {
//        viewModel.selectedIngredientList(sharedPreferences.getIngredientList())
        viewModel.selectedIngredientList(viewModel.changeRefrigeratorToIngredient(sharedPreferences.getIngredientList()))
        _ingredientListAdapter = viewModel.selectedIngredientArray.value?.let {
            IngredientListAdapter(
                it
            )
        }
        binding.rcvAddIngredientList.adapter = ingredientListAdapter
    }

    private fun changeListAdapter() {
        viewModel.selectedTypeId.observe(viewLifecycleOwner) {
            ingredientListAdapter.submitList(viewModel.ingredientList.value?.get(it)?.ingredientListItem)
        }
        selectedIngredientList()
    }

    private fun selectedIngredientList() {
        ingredientListAdapter.setOnIngredientClickListener {
            viewModel.selectedIngredientList(ingredientListAdapter.selectedIngredientArray)
        }
        setAddBtnEnabled()
    }

    private fun setAddBtnEnabled() {
        ingredientListAdapter.addEnabled.observe(viewLifecycleOwner) {
            viewModel.setAddBtnEnabled(it)
            clickAddBtn()
        }
    }

    private fun clickAddBtn() {
        binding.btnAddIngredientAdd.setOnClickListener {
            viewModel.selectedIngredientArray.observe(viewLifecycleOwner) { ingredientList ->
                sharedPreferences.storeIngredientIdList(
                    viewModel.changeIngredientToRefrigerator(
                        ingredientList
                    )
                )
                viewModel.updateStoredList(ingredientList)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _ingredientTypeAdapter = null
        _ingredientListAdapter = null
    }
}