package com.example.ceduliocezar.kointesting

import android.app.Application
import com.example.ceduliocezar.kointesting.model.DefaultHelloRepository
import com.example.ceduliocezar.kointesting.model.HelloRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class CustomApplication : Application() {

    val appModule = module {
        single<HelloRepository> { DefaultHelloRepository() }
        factory { HelloPresenter(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}