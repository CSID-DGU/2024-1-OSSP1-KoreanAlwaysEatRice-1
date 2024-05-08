package com.kaer.menuw.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaer.menuw.R
import com.kaer.menuw.databinding.DialogUpdateBinding
import com.kaer.menuw.util.base.BaseDialog

class UpdateDialog: BaseDialog() {

    private val binding: DialogUpdateBinding
        get() = requireNotNull(_binding as DialogUpdateBinding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogUpdateBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun setTitle() {
        binding.tvDialogUpdateTitle.text = title
    }

    override fun setContent() {
        binding.tvDialogUpdateContent.text = content
    }

    override fun setBtnClick(action: () -> Unit) {
        binding.tvDialogUpdateBtn.setOnClickListener { action() }
    }
}