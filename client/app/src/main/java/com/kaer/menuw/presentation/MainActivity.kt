package com.kaer.menuw.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMainBinding
import com.kaer.menuw.presentation.home.HomeFragment
import com.kaer.menuw.presentation.person.PersonFragment
import com.kaer.menuw.presentation.refrigerator.RefrigeratorFragment
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        initMainMenu()
    }

    private fun initMainMenu() {
        binding.bnvMain.selectedItemId = R.id.bnv_home

        binding.bnvMain.setOnItemSelectedListener { bnvItem ->
            when (bnvItem.itemId) {
                R.id.bnv_refrigerator -> changeFragment(RefrigeratorFragment())
                R.id.bnv_home -> changeFragment(HomeFragment())
                R.id.bnv_person -> changeFragment(PersonFragment())
                else -> true
            }
        }
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, HomeFragment())
                .commit()
        }
    }

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
        return true
    }
}