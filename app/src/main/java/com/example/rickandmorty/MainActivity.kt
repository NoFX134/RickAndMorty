package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Repository.Repository
import com.example.rickandmorty.adapter.CharacterAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleview()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCharacter(1)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                characterAdapter.seData(response.body()?.results)
            } else {
                Toast.makeText(
                    this, "Ошибка ${response.code().toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun setupRecycleview() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = characterAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}