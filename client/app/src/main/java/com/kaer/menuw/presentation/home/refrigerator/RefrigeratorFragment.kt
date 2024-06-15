package com.kaer.menuw.presentation.home.refrigerator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentRefrigeratorBinding
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientActivity
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel
import com.kaer.menuw.presentation.home.refrigerator.add.SharedPreferenceManager
import com.kaer.menuw.presentation.recommend.category.MenuCategoryActivity
import com.kaer.menuw.util.base.BaseDialog
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.Serializable

@AndroidEntryPoint
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
        initSetNotice()
        clickEditBtn()
        clickSeeRecommendBtn()
    }

    private fun initSetNotice() {
        viewModel.checkNeedNotice(sharedPreferences.getIngredientList())
        viewModel.needNotice.observe(viewLifecycleOwner) {
            Timber.d("공지 테스트 -> ${viewModel.noticeContent}")
            if (it) {
                binding.ivRefrigeratorNotice.setOnClickListener {
                    BaseDialog.Builder().build(
                        type = BaseDialog.DialogType.SINGLE,
                        title = "냉장고 재료 알림",
                        content = "\n유통기한이 지난/임박한 재료를 확인해주세요!\n" + viewModel.noticeContent,
                        doBtnText = "확인",
                        cancelBtnText = "",
                        doBtnAction = {},
                        cancelBtnAction = {}
                    ).show(parentFragmentManager, BaseDialog.DIALOG)
                }
            }
        }
    }

    private fun initSetRefrigerator() {
        _refrigeratorAdapter = RefrigeratorAdapter(requireContext())
        binding.rcvRefrigeratorList.adapter = refrigeratorAdapter
        refrigeratorAdapter.submitList(sharedPreferences.getIngredientList())
        Timber.d("저장된 재료 테스트1 -> ${sharedPreferences.getIngredientList()}")
        viewModel.setBackgroundTextVisible(sharedPreferences.getIngredientList().isEmpty())

//        initSetNotice()
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
                viewModel.setIngredientIdList(sharedPreferences.getIngredientList())
                viewModel.selectedIngredientIdArray.observe(viewLifecycleOwner) {
                    Timber.d("선택한 메뉴 아이디 전 -> $it")
                    intent.putExtra(INGREDIENT_ID_LIST, it as Serializable)
                    startActivity(intent)
                }
            } else {
                BaseDialog.Builder().build(
                    type = BaseDialog.DialogType.SINGLE,
                    title = "저장된 재료가 없어요!",
                    content = "메뉴를 추천 받기 전에 재료를 먼저 추가해 주세요",
                    doBtnText = "확인",
                    cancelBtnText = "",
                    doBtnAction = {},
                    cancelBtnAction = {}
                ).show(parentFragmentManager, BaseDialog.DIALOG)
            }
        }
    }

    private fun clickAddIngredientBtn() {
        val intent = Intent(requireActivity(), AddIngredientActivity::class.java)

        binding.btnRefrigeratorAddIngredient.setOnClickListener {
            viewModel.clickTypeId(0)
//            AddIngredientActivity().show(parentFragmentManager, BOTTOM_SHEET)
            activity?.finishAffinity()
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _refrigeratorAdapter = null
    }

    companion object {
        const val BOTTOM_SHEET = "BOTTOM_SHEET"
        const val INGREDIENT_ID_LIST = "INGREDIENT_ID_LIST"
    }
}