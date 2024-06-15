package com.kaer.menuw.presentation.home.refrigerator.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityAddIngredientBinding
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.presentation.home.HomeActivity
import com.kaer.menuw.presentation.home.refrigerator.RefrigeratorFragment.Companion.BOTTOM_SHEET
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.DAIRY_FOOD
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.FISH
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.GRAIN
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.MEAT
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.SEASONING
import com.kaer.menuw.presentation.home.refrigerator.add.AddIngredientViewModel.Companion.VEGETABLE
import com.kaer.menuw.presentation.home.refrigerator.add.checkdate.GetExpiryDateBottomSheet
import com.kaer.menuw.presentation.recommend.LoadingIndicator
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIngredientActivity :
    BaseActivity<ActivityAddIngredientBinding>(R.layout.activity_add_ingredient) {

    private val viewModel by viewModels<AddIngredientViewModel>()

    private lateinit var sharedPreferences: SharedPreferenceManager

    private var _ingredientTypeAdapter: IngredientTypeAdapter? = null
    private val ingredientTypeAdapter
        get() = requireNotNull(_ingredientTypeAdapter)

    private var _ingredientListAdapter: IngredientListAdapter? = null
    private val ingredientListAdapter
        get() = requireNotNull(_ingredientListAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        sharedPreferences = SharedPreferenceManager(this)

        val loadingProgress = LoadingIndicator(this@AddIngredientActivity)
        loadingProgress.show()

        viewModel.isLoading.observe(this) {
            if (!it) {
                loadingProgress.dismiss()
            }
        }

        initSetAdapter()
    }

    private fun initSetAdapter() {
        viewModel.getIngredientList()
        setTypeAdapter()
        changeListAdapter()
    }

    private fun setTypeAdapter() {
        _ingredientTypeAdapter = IngredientTypeAdapter().apply {
            setOnItemClickListener(object : IngredientTypeAdapter.OnItemClickListener {
                override fun onItemClick(item: IngredientTotal, position: Int) {
                    when (item.type) {
                        VEGETABLE -> viewModel.clickTypeId(0)
                        DAIRY_FOOD -> viewModel.clickTypeId(1)
                        GRAIN -> viewModel.clickTypeId(2)
                        MEAT -> viewModel.clickTypeId(3)
                        FISH -> viewModel.clickTypeId(4)
                        SEASONING -> viewModel.clickTypeId(5)
                        else -> viewModel.clickTypeId(0)
                    }
                }
            })
        }

        binding.rcvAddIngredientType.adapter = ingredientTypeAdapter
        viewModel.ingredientList.observe(this) {
            ingredientTypeAdapter.submitList(it)
            ingredientListAdapter.submitList(it[0].ingredientListItem)
        }
        makeListAdapter()
    }

    private fun makeListAdapter() {
//        viewModel.selectedIngredientList(sharedPreferences.getIngredientList())
        viewModel.selectedIngredientList(viewModel.changeRefrigeratorToIngredient(sharedPreferences.getIngredientList()))
        _ingredientListAdapter = viewModel.selectedIngredientArray.value?.let {
            IngredientListAdapter(
                it
            )
        }
        binding.rcvAddIngredientList.adapter = ingredientListAdapter
    }

    private fun changeListAdapter() {
        viewModel.selectedTypeId.observe(this) {
            ingredientListAdapter.submitList(viewModel.ingredientList.value?.get(it)?.ingredientListItem)
        }
        selectedIngredientList()
    }

    private fun selectedIngredientList() {
        ingredientListAdapter.setOnIngredientClickListener {
            viewModel.selectedIngredientList(ingredientListAdapter.selectedIngredientArray)
        }
        setAddBtnEnabled()
    }

    private fun setAddBtnEnabled() {
        ingredientListAdapter.addEnabled.observe(this) {
            viewModel.setAddBtnEnabled(it)
            clickAddBtn()
        }
    }

    private fun clickAddBtn() {
        val intent = Intent(this, HomeActivity::class.java)
        binding.btnAddIngredientAdd.setOnClickListener {

            viewModel.selectedIngredientArray.observe(this) { ingredients ->
                GetExpiryDateBottomSheet().show(supportFragmentManager, BOTTOM_SHEET)
                viewModel.expiryDate.observe(this) { dates ->
                    sharedPreferences.storeIngredientIdList(
                        viewModel.changeIngredientToRefrigerator(
                            dates,
                            ingredients
                        )
                    )

                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _ingredientTypeAdapter = null
        _ingredientListAdapter = null
    }
}