package com.ceduliocezar.koin.base

import com.ceduliocezar.koin.threading.AsyncOperation
import com.ceduliocezar.koin.threading.AsyncProvider

abstract class BasePresenter(private val asyncProvider: AsyncProvider){

    fun <T> onBackground(function: () -> T): AsyncOperation<T> {
        return asyncProvider.create(function)
    }
}