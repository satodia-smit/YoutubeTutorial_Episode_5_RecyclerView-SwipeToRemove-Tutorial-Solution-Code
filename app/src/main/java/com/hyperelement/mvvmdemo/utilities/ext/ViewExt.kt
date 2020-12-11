package com.hyperelement.mvvmdemo.utilities.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <M : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutRes: Int): M {
    return DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layoutRes,
        this,
        false
    )
}