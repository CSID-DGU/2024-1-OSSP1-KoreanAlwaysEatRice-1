package com.kaer.menuw.presentation.recommend

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.kaer.menuw.R

class LoadingIndicator(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_indicator)
        setCancelable(false)

        window!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context,
                    android.R.color.transparent
                )
            )
        )
    }
}