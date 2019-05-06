package com.ceduliocezar.koin.threading

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource
import java.util.concurrent.Executor


interface AsyncOperation<T> {
    fun onSuccess(execute: (it: T) -> Unit): AsyncOperation<T>
    fun onError(execute: (error: Throwable) -> Unit): AsyncOperation<T>
    fun execute()
}

class AsyncOperationImpl<T>(
    private val onBackground: () -> T,
    private val countingIdlingResource: CountingIdlingResource,
    private val functionExecutor: Executor,
    private val postExecutionExecutor: Executor
) : AsyncOperation<T> {

    private var onSuccess: ((it: T) -> Unit)? = null
    private var onError: ((error: Throwable) -> Unit)? = null

    override fun onSuccess(execute: (it: T) -> Unit): AsyncOperation<T> {
        this.onSuccess = execute
        return this
    }

    override fun onError(execute: (error: Throwable) -> Unit): AsyncOperation<T> {
        this.onError = execute
        return this
    }

    override fun execute() {
        Log.d("AsyncOperation:", "execute")
        countingIdlingResource.increment()
        functionExecutor.execute {
            Log.d("AsyncOperation:", "functionExecutor: execute")
            try {
                val result = onBackground()
                postExecutionExecutor.execute {
                    onSuccess?.invoke(result)
                    countingIdlingResource.decrement()
                }
            } catch (throwable: Throwable) {
                postExecutionExecutor.execute {
                    onError?.invoke(throwable)
                    countingIdlingResource.decrement()
                }
            }
        }
    }
}