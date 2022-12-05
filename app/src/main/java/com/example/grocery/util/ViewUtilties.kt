package com.example.grocery.util

import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.textfield.TextInputLayout

fun <T : ViewDataBinding> ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToParent: Boolean = false
): T {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, attachToParent)
}

@BindingAdapter("isVisible")
fun View.isVisible(boolean: Boolean) {
    isVisible = boolean
}

@BindingAdapter("isStrike")
fun TextView.isStrike(strikeThrough: Boolean) {
    if (strikeThrough) {
        this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("app:errorText")
fun setErrorText(view: TextInputLayout, errorMessage: String) {
    if (errorMessage.isEmpty())
        view.error = null
    else
        view.error = errorMessage;
}


class TextFieldValidation(private val view: View) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}