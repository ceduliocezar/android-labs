package com.ceduliocezar.koin.threading

import android.os.Handler
import android.util.Log
import java.util.concurrent.Executor

class MainThreadExecutor(private val handler: Handler) : Executor {
    override fun execute(command: Runnable?) {
        Log.d("MainThreadExecutor", "execute")
        handler.post(command)
    }
}