package com.ceduliocezar.koin.presentation.presenter

import com.ceduliocezar.koin.base.BasePresenter
import com.ceduliocezar.koin.domain.HelloRepository
import com.ceduliocezar.koin.threading.AsyncProvider

class DefaultSimplePresenter(
    private val repository: HelloRepository,
    asyncProvider: AsyncProvider
) : BasePresenter(asyncProvider), SimplePresenter {

    private var view: PresenterView? = null

    override fun onCreate(view: PresenterView) {
        this.view = view
        init()
    }

    private fun init() {

        onBackground {
            repository.giveSlowHello()
        }.onSuccess {
            this.view?.showText(it)
        }.onError {
            it.message?.let { message ->
                view?.showText(message)
            }
        }
    }

    override fun onDestroy() {
        this.view = null
    }
}