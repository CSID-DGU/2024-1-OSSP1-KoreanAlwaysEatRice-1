package com.kaer.menuw.presentation.recommend

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMenuListBinding
import com.kaer.menuw.presentation.home.HomeActivity
import com.kaer.menuw.presentation.recommend.category.MenuCategoryActivity.Companion.RECOMMEND_REQUEST_INTENT
import com.kaer.menuw.presentation.recommend.category.model.RecommendRequestIntent
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MenuListActivity :
    BaseActivity<ActivityMenuListBinding>(R.layout.activity_menu_list) {

    private val viewModel by viewModels<MenuListViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setStatusBarColorFromResource(R.color.background)

        val loadingProgress = LoadingIndicator(this@MenuListActivity)
        loadingProgress.show()

        viewModel.isLoading.observe(this) {
            if (!it) {
                loadingProgress.dismiss()
            }
        }

        clickBackBtn()
        initSetTabPage()
        clickTabItem()
        initPostRecommendMenuList()
    }

    private fun initPostRecommendMenuList() {
        Timber.d(
            "request intent test -> ${
                intent.getParcelableExtra<RecommendRequestIntent>(
                    RECOMMEND_REQUEST_INTENT
                )
            }"
        )
        val intentData = intent.getParcelableExtra<RecommendRequestIntent>(RECOMMEND_REQUEST_INTENT)
        intentData?.let {
            viewModel.postRecommendMenuList(
                it.recipe, it.menuType, it.ingredientList
            )
        }
    }

    private fun initSetTabPage() {
        supportFragmentManager.commit {
            replace(R.id.fcv_menu_list, MenuRecommendFragment())
        }
    }

    private fun clickTabItem() {
        binding.layoutMenuListTab.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> replaceFragment(MenuRecommendFragment())
                    1 -> replaceFragment(MenuNotRecommendFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}


        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fcv_menu_list, fragment)
        }
    }

    private fun clickBackBtn() {
        val intent = Intent(this, HomeActivity::class.java)
        binding.btnIngredientRecommendMenuBack.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}