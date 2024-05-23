package com.kaer.menuw.presentation.home.refrigerator.recommend.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMenuCategoryBinding
import com.kaer.menuw.util.base.BaseActivity

class MenuCategoryActivity: BaseActivity<ActivityMenuCategoryBinding>(R.layout.activity_menu_category) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecipePage()
        clickBackBtn()
    }

    private fun initRecipePage() {
        supportFragmentManager.findFragmentById(R.id.fcv_menu_category) ?: navigateTo<CategoryRecipeFragment>()
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
}