package com.kaer.menuw.presentation.home.refrigerator.recommend

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMenuListBinding
import com.kaer.menuw.presentation.home.HomeActivity
import com.kaer.menuw.util.base.BaseActivity

class MenuListActivity :
    BaseActivity<ActivityMenuListBinding>(R.layout.activity_menu_list) {

    private val viewModel by viewModels<MenuListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        clickBackBtn()
        initSetTabPage()
        clickTabItem()
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