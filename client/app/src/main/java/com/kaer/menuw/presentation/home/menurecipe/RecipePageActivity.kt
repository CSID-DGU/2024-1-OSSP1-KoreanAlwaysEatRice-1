package com.kaer.menuw.presentation.home.menurecipe

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityRecipePageBinding
import com.kaer.menuw.util.base.BaseActivity
import timber.log.Timber

class RecipePageActivity: BaseActivity<ActivityRecipePageBinding>(R.layout.activity_recipe_page) {

    private val viewModel by viewModels<RecipePageViewModel>()

    private var _recipePageAdapter: RecipePageAdapter? = null
    val recipePageAdapter: RecipePageAdapter
        get() = requireNotNull(_recipePageAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetAdapter()
        setEvaluateBtnEnabled()
        setRecipePageProgress()
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
            }
        })
    }

    private fun setRecipePageProgress() {
        viewModel.progressPercent.observe(this) {
            Timber.d("진행률 -> $it")
            binding.progressbarRecipePage.progress = it
        }
    }
}