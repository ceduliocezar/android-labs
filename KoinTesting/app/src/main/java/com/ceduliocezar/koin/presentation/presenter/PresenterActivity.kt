package com.ceduliocezar.koin.presentation.presenter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ceduliocezar.koin.R
import org.koin.android.ext.android.inject

class PresenterActivity : AppCompatActivity(), PresenterView {

    private val presenter: SimplePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)
    }

    override fun showText(text: String) {
        findViewById<TextView>(R.id.text).text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
