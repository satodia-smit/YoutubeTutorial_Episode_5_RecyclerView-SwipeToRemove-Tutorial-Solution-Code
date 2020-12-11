package com.hyperelement.mvvmdemo.di

import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.ui.fragment.fragmentone.FragmentOneViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Inject ViewModels
    viewModel {
        FragmentOneViewModel(
            get()
        )
    }


    // Inject Repository
    single { FragmentOneRepository(androidContext()) }

}
