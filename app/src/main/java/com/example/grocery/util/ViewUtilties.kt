package com.example.grocery.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

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