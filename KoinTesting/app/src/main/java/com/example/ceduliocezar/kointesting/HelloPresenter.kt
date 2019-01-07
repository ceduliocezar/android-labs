package com.example.ceduliocezar.kointesting

import com.example.ceduliocezar.kointesting.model.HelloRepository


class HelloPresenter(val helloRepository: HelloRepository){


    fun sayHello() = "${helloRepository.giveHello()} from $this"
}