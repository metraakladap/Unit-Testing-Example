
package com.example.unittestingexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import android.widget.Button
import android.widget.TextView
import com.example.unittestingexample.R

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button
    private val apiService = ApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            fetchBitcoinRate()
        }
    }

    private fun fetchBitcoinRate() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getBitcoinRate()
                withContext(Dispatchers.Main) {
                    textView.text = "Bitcoin Rate: ${response.data.rateUsd} USD"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    textView.text = "Error: ${e.message}"
                }
            }
        }
    }
}
