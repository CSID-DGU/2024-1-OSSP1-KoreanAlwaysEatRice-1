package com.kaer.menuw.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaer.menuw.databinding.DialogSingleBinding
import com.kaer.menuw.util.base.BaseDialog

class SingleDialog : BaseDialog() {

    private val binding: DialogSingleBinding
        get() = requireNotNull(_binding as DialogSingleBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogSingleBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun setTitle() {
        binding.tvDialogUpdateTitle.text = title
    }

    override fun setContent() {
        binding.tvDialogUpdateContent.text = content
    }

    override fun setDoBtnText() {
        binding.tvDialogUpdateBtn.text = doBtnText
    }

    override fun setCancelBtnText() {
    }

    override fun setDoBtnClick(action: () -> Unit) {
        binding.tvDialogUpdateBtn.setOnClickListener { action() }
    }

    override fun setCancelBtnClick(action: () -> Unit) {
    }
}