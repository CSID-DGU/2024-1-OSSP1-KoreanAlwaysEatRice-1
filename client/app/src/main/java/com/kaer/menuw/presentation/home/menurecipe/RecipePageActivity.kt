package com.kaer.menuw.presentation.home.menurecipe

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityRecipePageBinding
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RecipePageActivity: BaseActivity<ActivityRecipePageBinding>(R.layout.activity_recipe_page) {

    private val viewModel by viewModels<RecipePageViewModel>()

    private var _recipePageAdapter: RecipePageAdapter? = null
    val recipePageAdapter: RecipePageAdapter
        get() = requireNotNull(_recipePageAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickBackBtn()
        initSetAdapter()
        setEvaluateBtnEnabled()
        setRecipePageProgress()
    }

    private fun clickBackBtn() {
        binding.btnRecipePageBack.setOnClickListener {
            finish()
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
                viewModel.setProgressPercent(position+1, recipePageAdapter.itemList.size)

                if (position == recipePageAdapter.itemList.size - 1) {
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
                title = "메뉴 평가하기",
                content = "추천받은 메뉴가 \n" +
                        "내 선호도에 적합했는지 평가해주세요!",
                btnAction = {}
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