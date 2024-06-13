package com.kaer.menuw.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaer.menuw.databinding.DialogDoubleBinding
import com.kaer.menuw.util.base.BaseDialog

class DoubleDialog : BaseDialog() {

    private val binding: DialogDoubleBinding
        get() = requireNotNull(_binding as DialogDoubleBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogDoubleBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun setTitle() {
        binding.tvDialogDoubleTitle.text = title
    }

    override fun setContent() {
        binding.tvDialogDoubleContent.text = content
    }

    override fun setDoBtnText() {
        binding.tvDialogDoubleDo.text = doBtnText
    }

    override fun setCancelBtnText() {
        binding.tvDialogDoubleCancel.text = cancelBtnText
    }

    override fun setDoBtnClick(action: () -> Unit) {
        binding.tvDialogDoubleDo.setOnClickListener { action() }
    }

    override fun setCancelBtnClick(action: () -> Unit) {
        binding.tvDialogDoubleCancel.setOnClickListener { action() }
    }
}