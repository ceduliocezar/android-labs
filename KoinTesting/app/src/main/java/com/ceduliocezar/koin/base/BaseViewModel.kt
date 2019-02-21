package com.ceduliocezar.koin.base

import androidx.lifecycle.ViewModel
import com.ceduliocezar.koin.threading.AsyncOperation
import com.ceduliocezar.koin.threading.AsyncProvider


abstract class BaseViewModel(private val asyncProvider: AsyncProvider) : ViewModel() {

    fun <T> onBackground(function: () -> T): AsyncOperation<T> {
        return asyncProvider.create(function)
    }
}