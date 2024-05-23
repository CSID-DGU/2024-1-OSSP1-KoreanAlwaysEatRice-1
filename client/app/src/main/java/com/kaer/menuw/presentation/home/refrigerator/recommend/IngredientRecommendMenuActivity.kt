package com.kaer.menuw.presentation.home.refrigerator.recommend

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityIngredientRecommendMenuBinding
import com.kaer.menuw.presentation.home.HomeActivity
import com.kaer.menuw.util.base.BaseActivity

class IngredientRecommendMenuActivity :
    BaseActivity<ActivityIngredientRecommendMenuBinding>(R.layout.activity_ingredient_recommend_menu) {

    private val viewModel by viewModels<IngredientRecommendMenuViewModel>()

    private var _recommendMenuAdapter: RecommendMenuAdapter? = null
    private val recommendMenuAdapter
        get() = requireNotNull(_recommendMenuAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        clickBackBtn()
        initSetRecommendMenuList()
    }

    private fun initSetRecommendMenuList() {
        _recommendMenuAdapter = RecommendMenuAdapter()
        binding.rcvIngredientRecommendList.adapter = recommendMenuAdapter
        viewModel.mockMenuList.observe(this) {
            recommendMenuAdapter.submitList(it)
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