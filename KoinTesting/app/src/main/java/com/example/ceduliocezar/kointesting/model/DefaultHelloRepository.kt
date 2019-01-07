package com.example.ceduliocezar.kointesting.model


class DefaultHelloRepository : HelloRepository {
    override fun giveHello() = "Hello Koin"


}