package com.kaer.menuw.presentation.home.refrigerator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientBottomSheet
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.home.refrigerator.add.SharedPreferenceManager
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryActivity
import com.kaer.menuw.util.base.BaseDialog
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
        clickEditBtn()
        clickSeeRecommendBtn()
    }

    private fun initSetRefrigerator() {
        _refrigeratorAdapter = RefrigeratorAdapter()
        binding.rcvRefrigeratorList.adapter = refrigeratorAdapter
        refrigeratorAdapter.submitList(sharedPreferences.getIngredientList())
        viewModel.setBackgroundTextVisible(sharedPreferences.getIngredientList().isEmpty())

        updateRefrigerator()
    }

    private fun updateRefrigerator() {
        viewModel.updateStoredIngredientArray.observe(viewLifecycleOwner) {
            refrigeratorAdapter.submitList(it)
            viewModel.setBackgroundTextVisible(it.isEmpty())
        }
    }

    private fun clickEditBtn() {
        binding.tvRefrigeratorEdit.setOnClickListener {
            if (viewModel.deleteBtnVisible.value == true) {
                viewModel.setDeleteBtnVisible(false)
                refrigeratorAdapter.editEnabled.value = false
            } else {
                viewModel.setDeleteBtnVisible(true)
                refrigeratorAdapter.editEnabled.value = true
                setDeleteIngredient()
            }
        }
    }

    private fun setDeleteIngredient() {
        refrigeratorAdapter.setOnIngredientClickListener {
            viewModel.setDeleteEnabled(refrigeratorAdapter.selectedIngredientArray.isNotEmpty())
            viewModel.deleteEnabled.observe(viewLifecycleOwner) {
                binding.btnRefrigeratorDelete.setOnClickListener {
                    deleteIngredient()
                }
            }
        }
    }

    private fun deleteIngredient() {
        for (i in 0 until refrigeratorAdapter.selectedIngredientArray.size) {
            sharedPreferences.removeIngredientItem(refrigeratorAdapter.selectedIngredientArray[i])
        }

        refrigeratorAdapter.submitList(sharedPreferences.getIngredientList())
        viewModel.setBackgroundTextVisible(sharedPreferences.getIngredientList().isEmpty())
        viewModel.setDeleteBtnVisible(false)
    }

    private fun clickSeeRecommendBtn() {
        val intent = Intent(requireActivity(), MenuCategoryActivity::class.java)
        binding.btnRefrigeratorSeeRecommend.setOnClickListener {
            if (sharedPreferences.getIngredientList().isNotEmpty()) {
                startActivity(intent)
            } else {
                BaseDialog.Builder().build(
                    title = "저장된 재료가 없어요!",
                    content = "메뉴를 추천 받기 전에 재료를 먼저 추가해 주세요",
                    btnAction = {}
                ).show(parentFragmentManager, BaseDialog.DIALOG)
            }
        }
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