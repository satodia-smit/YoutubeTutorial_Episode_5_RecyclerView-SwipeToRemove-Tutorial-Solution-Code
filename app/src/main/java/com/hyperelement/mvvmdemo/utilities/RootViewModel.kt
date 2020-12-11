package com.hyperelement.mvvmdemo.utilities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class RootViewModel : ViewModel(), CoroutineScope {

    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    override fun onCleared() {
        Timber.e("VIEW MODEL CLEARED $this")
        super.onCleared()
        parentJob.cancel()
    }
}