package com.kaer.menuw.presentation.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityHomeBinding
import com.kaer.menuw.presentation.home.likemenu.LikeMenuListFragment
import com.kaer.menuw.presentation.home.refrigerator.RefrigeratorFragment
import com.kaer.menuw.presentation.mypage.MyPageActivity
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColorFromResource(R.color.background_bright)

        viewModel.getUserInfo()

        clickBackBtn()

        initSetTabPage()
        clickTabItem()
        clickSetting()
    }

    private fun clickBackBtn() {
        onBackPressedDispatcher.addCallback {
            if (System.currentTimeMillis() - backPressedTime >= BACK_PRESSED_TIME) {
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(applicationContext, "앱을 종료하려면 한 번 더 눌러주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                finishAffinity()
            }
        }
    }

    private fun clickSetting() {
        val intent = Intent(this, MyPageActivity::class.java)
        binding.ivHomeSetting.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun initSetTabPage() {
        supportFragmentManager.commit {
            replace(R.id.fcv_home_tab_main, RefrigeratorFragment())
        }
    }

    private fun clickTabItem() {
        binding.layoutHomeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> replaceFragment(RefrigeratorFragment())
                    1 -> replaceFragment(LikeMenuListFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}


        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fcv_home_tab_main, fragment)
        }
    }

    companion object {
        const val BACK_PRESSED_TIME = 2000L
    }
}