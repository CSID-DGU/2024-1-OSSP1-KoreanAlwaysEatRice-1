package com.kaer.menuw.presentation.home.menurecipe

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityRecipePageBinding
import com.kaer.menuw.presentation.home.refrigerator.recommend.MenuListViewModel.Companion.CHOOSE_MENU
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RecipePageActivity : BaseActivity<ActivityRecipePageBinding>(R.layout.activity_recipe_page) {

    private val viewModel by viewModels<RecipePageViewModel>()

    private var _recipePageAdapter: RecipePageAdapter? = null
    val recipePageAdapter: RecipePageAdapter
        get() = requireNotNull(_recipePageAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickBackBtn()
        initSetAdapter()
        initGetRecipeList()
        setEvaluateBtnEnabled()
        setRecipePageProgress()
    }

    private fun clickBackBtn() {
        binding.btnRecipePageBack.setOnClickListener {
            finish()
        }
    }

    private fun initGetRecipeList() {
        intent.getStringExtra(CHOOSE_MENU)?.let { viewModel.postRecipeList(it) }
        viewModel.recipeList.observe(this) {
            viewModel.mapRecipeItemList(it)
        }
        viewModel.recipeItemList.observe(this) {
            recipePageAdapter.submitList(it)
        }
    }

    private fun initSetAdapter() {
        _recipePageAdapter = RecipePageAdapter()
        binding.viewpagerRecipePage.adapter = recipePageAdapter
    }

    private fun setEvaluateBtnEnabled() {
        binding.viewpagerRecipePage.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setProgressPercent(position + 1, recipePageAdapter.currentList.size)

                if (position == recipePageAdapter.currentList.size - 1) {
                    binding.btnRecipePageEvaluate.visibility = View.VISIBLE
                    binding.btnRecipePageEvaluate.isEnabled = true
                }

                clickEvaluateBtn()
            }
        })
    }

    private fun clickEvaluateBtn() {
        binding.btnRecipePageEvaluate.setOnClickListener {
            BaseDialog.Builder().build(
                type = BaseDialog.DialogType.DOUBLE,
                title = "메뉴 평가하기",
                content = "추천받은 메뉴가 \n" +
                        "내 선호도에 적합했는지 평가해주세요!",
                doBtnText = "좋았다",
                cancelBtnText = "별로였다",
                doBtnAction = {
                    viewModel.patchMenuLike(2)
                    viewModel.isMenuLikeValid.observe(this) {
                        if (it) {
                            finish()
                        }
                    }
                },
                cancelBtnAction = {
                    viewModel.patchMenuLike(1)
                    viewModel.isMenuLikeValid.observe(this) {
                        if (it) {
                            finish()
                        }
                    }
                }
            ).show(supportFragmentManager, BaseDialog.DIALOG)
        }
    }

    private fun setRecipePageProgress() {
        viewModel.progressPercent.observe(this) {
            Timber.d("진행률 -> $it")
            binding.progressbarRecipePage.progress = it
        }
    }
}