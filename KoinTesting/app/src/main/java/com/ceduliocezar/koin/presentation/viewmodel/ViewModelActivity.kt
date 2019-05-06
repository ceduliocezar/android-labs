package com.ceduliocezar.koin.presentation.viewmodel


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ceduliocezar.koin.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class ViewModelActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)


        val observer = Observer<String> { newName ->
            findViewById<TextView>(R.id.text).text = newName
        }


        viewModel.liveDataString.observe(this, observer)
        viewModel.displayGeneralErrorMessage.observe(this, Observer<Boolean> {
            if (it) {
                findViewById<TextView>(R.id.error).visibility = View.VISIBLE
            } else {
                findViewById<TextView>(R.id.error).visibility = View.GONE
            }
        })
    }
}
