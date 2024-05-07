package com.kaer.menuw.util.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.kaer.menuw.util.UpdateDialog

abstract class BaseDialog : DialogFragment() {

    protected var _binding: ViewBinding? = null
    protected var title: String? = null
    protected var content: String? = null
    protected lateinit var btnAction: () -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        setContent()
        setBtnClick { btnAction(); dismiss() }
    }

    abstract fun setTitle()
    abstract fun setContent()
    abstract fun setBtnClick(action: () -> Unit)

    class Builder() {
        fun build(
            title: String,
            content: String,
            btnAction: () -> Unit
        ): BaseDialog {
            val dialog = UpdateDialog()
            return dialog.apply {
                this.title = title
                this.content = content
                this.btnAction = btnAction
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}