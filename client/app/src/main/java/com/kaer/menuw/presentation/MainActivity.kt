package com.kaer.menuw.presentation

import android.os.Bundle
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMainBinding
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}