package com.example.ceduliocezar.kointesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val helloPresenter: HelloPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", helloPresenter.sayHello())
    }
}
