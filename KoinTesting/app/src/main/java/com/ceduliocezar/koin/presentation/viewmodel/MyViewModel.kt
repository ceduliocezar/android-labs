package com.ceduliocezar.koin.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ceduliocezar.koin.base.BaseViewModel
import com.ceduliocezar.koin.domain.HelloRepository
import com.ceduliocezar.koin.threading.AsyncProvider

class MyViewModel(
    private val repository: HelloRepository,
    asyncProvider: AsyncProvider
) : BaseViewModel(asyncProvider) {

    val liveDataString: MutableLiveData<String> = MutableLiveData()
    val displayGeneralErrorMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {
        onBackground {
            repository.giveSlowHello()
        }.onSuccess {
            liveDataString.value = it
        }.onError {
            displayGeneralErrorMessage.value = true
        }.execute()
    }
}