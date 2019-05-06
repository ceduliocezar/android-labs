package com.ceduliocezar.koin.presentation.presenter

interface SimplePresenter {
    fun onCreate(view: PresenterView)
    fun onDestroy()
}