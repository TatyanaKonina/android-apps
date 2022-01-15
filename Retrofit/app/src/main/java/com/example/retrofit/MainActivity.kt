package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://cat-fact.herokuapp.com"
val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()

        findViewById<Button>(R.id.button).setOnClickListener{
            getCurrentData()
        }

    }

    private fun getCurrentData() {
        findViewById<TextView>(R.id.tv_textView).visibility = View.GONE
        findViewById<TextView>(R.id.tv_timeStamp).visibility = View.GONE
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCatFacts().awaitResponse()
                if (response.isSuccessful) {

                    val data = response.body()!!
                    Log.d(TAG, data.toString())

                    withContext(Dispatchers.Main) {
                        findViewById<TextView>(R.id.tv_textView).visibility = View.VISIBLE
                        findViewById<TextView>(R.id.tv_timeStamp).visibility = View.VISIBLE
                        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                        findViewById<TextView>(R.id.tv_timeStamp).text = data.createdAt
                        findViewById<TextView>(R.id.tv_textView).text = data.text

                    }

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        applicationContext,
                        "Seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }
}