package com.ceduliocezar.koin.domain

import android.util.Log

interface HelloRepository {
    fun giveSlowHello(): String
}

class HelloRepositoryImpl(private val defaultDelayInMillis: Long) : HelloRepository {
    override fun giveSlowHello(): String {
        Log.d("HelloRepositoryImpl", "giveSlowHello")
        Thread.sleep(defaultDelayInMillis)
        return "Hello from HelloRepositoryImpl"
    }
}