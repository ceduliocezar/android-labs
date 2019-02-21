package com.ceduliocezar.koin.threading

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource
import java.util.concurrent.Executor

interface AsyncProvider {
    fun <T> create(function: () -> T): AsyncOperation<T>
}

class AsyncProviderImpl(
    private val countingIdlingResource: CountingIdlingResource,
    private val backgroundExecutor: Executor,
    private val postExecutionExecutor: Executor
) : AsyncProvider {

    override fun <T> create(function: () -> T): AsyncOperation<T> {
        Log.d("AsyncProviderImpl", "create")
        return AsyncOperationImpl(function, countingIdlingResource, backgroundExecutor, postExecutionExecutor)
    }
}
