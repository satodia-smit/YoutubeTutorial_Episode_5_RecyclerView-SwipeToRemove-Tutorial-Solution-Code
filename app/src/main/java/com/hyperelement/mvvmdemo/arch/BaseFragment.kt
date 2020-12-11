package com.hyperelement.mvvmdemo.arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel>(
    @LayoutRes val layout: Int,
    private val viewModelClass: KClass<VM>
) : Fragment() {

    // Accessible, but generic, won't have specific methods/fields, see getSpecificBinding()
    open var binding: ViewDataBinding? = null

    open lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        viewModel = getViewModel(viewModelClass)
        //If binding available, return it's root otherwise just inflate normally, binding stays null
        return binding?.root ?: inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // https://stackoverflow.com/a/57763743/7968986 , xml viewModel has to be named "viewModel"
        binding?.setVariable(BR.viewModel, viewModel)
    }

    inline fun <reified specific : ViewDataBinding> getSpecificBinding() = binding as? specific
}