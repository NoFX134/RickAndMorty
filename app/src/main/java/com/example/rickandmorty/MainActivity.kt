package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.Repository.Repository


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCharacter(1)
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val resultList = response.body()?.results
                resultList?.forEach {
                    Log.d("Response", it?.name.toString())
                    Log.d("Response", it?.gender.toString())
                    Log.d("Response", it?.image.toString())
                    Log.d("Response", "xxxxxxxxxxxxxxxxxxx ")
                }
            } else {
                Log.d("Response", response.code().toString())
            }
        }
    }
}