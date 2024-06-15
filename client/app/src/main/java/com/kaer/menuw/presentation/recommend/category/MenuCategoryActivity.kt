package com.kaer.menuw.presentation.recommend.category

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMenuCategoryBinding
import com.kaer.menuw.presentation.home.refrigerator.RefrigeratorFragment.Companion.INGREDIENT_ID_LIST
import com.kaer.menuw.presentation.recommend.MenuListActivity
import com.kaer.menuw.presentation.recommend.category.MenuCategoryViewModel.Companion.RECOMMEND_PAGE
import com.kaer.menuw.presentation.recommend.category.MenuCategoryViewModel.Companion.TYPE_PAGE
import com.kaer.menuw.presentation.recommend.category.model.RecommendRequestIntent
import com.kaer.menuw.presentation.recommend.category.recipe.CategoryRecipeFragment
import com.kaer.menuw.presentation.recommend.category.type.CategoryTypeFragment
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MenuCategoryActivity :
    BaseActivity<ActivityMenuCategoryBinding>(R.layout.activity_menu_category) {

    private val viewModel by viewModels<MenuCategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarColorFromResource(R.color.background)

        Timber.d("선택한 메뉴 아이디 후 -> ${intent.getSerializableExtra(INGREDIENT_ID_LIST) as? ArrayList<Int>}")
        (intent.getSerializableExtra(INGREDIENT_ID_LIST) as? ArrayList<Int>)?.let {
            viewModel.setSelectedIngredientIdList(
                it
            )
        }

        initRecipePage()
        clickBackBtn()
        changeToTypePage()
    }

    private fun initRecipePage() {
        supportFragmentManager.findFragmentById(R.id.fcv_menu_category)
            ?: navigateTo<CategoryRecipeFragment>()
    }

    private fun changeToTypePage() {
        viewModel.currentCategoryPage.observe(this) {
            when (it) {
                TYPE_PAGE -> navigateTo<CategoryTypeFragment>()
                RECOMMEND_PAGE -> {
                    val requestIntent = viewModel.selectedCategory.value?.let { it1 ->
                        viewModel.selectedType.value?.let { it2 ->
                            viewModel.selectedIngredientId.value?.let { it3 ->
                                RecommendRequestIntent(
                                    it1, it2, it3
                                )
                            }
                        }
                    }
                    val intent = Intent(this, MenuListActivity::class.java)
                    intent.putExtra(RECOMMEND_REQUEST_INTENT, requestIntent)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun clickBackBtn() {
        binding.btnMenuCategoryBack.setOnClickListener {
            finish()
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_menu_category, T::class.simpleName)
        }
    }

    companion object {
        const val RECOMMEND_REQUEST_INTENT = "RECOMMEND_REQUEST_INTENT"
    }
}