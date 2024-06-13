package com.kaer.menuw.util.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.kaer.menuw.util.DoubleDialog
import com.kaer.menuw.util.SingleDialog

abstract class BaseDialog : DialogFragment() {

    protected var _binding: ViewBinding? = null
    protected var title: String? = null
    protected var content: String? = null
    protected var doBtnText: String? = null
    protected var cancelBtnText: String? = null
    protected lateinit var doBtnAction: () -> Unit
    protected lateinit var cancelBtnAction: () -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        setContent()
        setDoBtnText()
        setCancelBtnText()
        setDoBtnClick { doBtnAction(); dismiss() }
        setCancelBtnClick { cancelBtnAction(); dismiss() }
    }

    abstract fun setTitle()
    abstract fun setContent()
    abstract fun setDoBtnText()
    abstract fun setCancelBtnText()
    abstract fun setDoBtnClick(action: () -> Unit)
    abstract fun setCancelBtnClick(action: () -> Unit)

    class Builder() {
        fun build(
            type: DialogType,
            title: String,
            content: String,
            doBtnText: String,
            cancelBtnText: String,
            doBtnAction: () -> Unit,
            cancelBtnAction: () -> Unit
        ): BaseDialog {
//            val dialog = SingleDialog()
            return type.getInstance().apply {
                this.title = title
                this.content = content
                this.doBtnText = doBtnText
                this.cancelBtnText = cancelBtnText
                this.doBtnAction = doBtnAction
                this.cancelBtnAction = cancelBtnAction
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val DIALOG = "DIALOG"
    }

    enum class DialogType {
        SINGLE {
            override fun getInstance(): BaseDialog = SingleDialog()
        },
        DOUBLE {
            override fun getInstance(): BaseDialog = DoubleDialog()
        },
        ;

        abstract fun getInstance(): BaseDialog
    }
}