package com.ceduliocezar.koin

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.test.espresso.idling.CountingIdlingResource
import com.ceduliocezar.koin.domain.HelloRepository
import com.ceduliocezar.koin.domain.HelloRepositoryImpl
import com.ceduliocezar.koin.presentation.presenter.DefaultSimplePresenter
import com.ceduliocezar.koin.presentation.presenter.SimplePresenter
import com.ceduliocezar.koin.presentation.viewmodel.MyViewModel
import com.ceduliocezar.koin.threading.AsyncProvider
import com.ceduliocezar.koin.threading.AsyncProviderImpl
import com.ceduliocezar.koin.threading.MainThreadExecutor
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CustomApplication : Application() {

    lateinit var countingIdlingResource: CountingIdlingResource

    private val appModule = module {
        single<Long>(name = "default_delay_in_millis") { 3000 }
        single<HelloRepository> { HelloRepositoryImpl(get("default_delay_in_millis")) }
        factory { DefaultSimplePresenter(get(), get()) as SimplePresenter }

        single<Executor>(name = "mainExecutor") { MainThreadExecutor(get()) }
        factory { Handler(get<Looper>()) }
        factory { Looper.getMainLooper() }
        factory<Executor>(name = "backgroundExecutor") { Executors.newSingleThreadExecutor() }
        single { CountingIdlingResource("generalCountIdlingResource") }
        single<AsyncProvider> { AsyncProviderImpl(get(), get("backgroundExecutor"), get("mainExecutor")) }

        viewModel { MyViewModel(get(), get()) }
    }


    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
        this.countingIdlingResource = get()
    }

}