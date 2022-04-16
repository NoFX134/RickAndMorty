package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Repository.Repository
import com.example.rickandmorty.adapter.CharacterAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var nextPage: Button
    private lateinit var previousPage: Button
    private val characterAdapter by lazy { CharacterAdapter() }
    private var userId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextPage = findViewById(R.id.nextPage)
        previousPage = findViewById(R.id.previousPage)

        setupRecycleview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        request(userId)

        nextPage.setOnClickListener {
            if (userId < 42) {
                userId += 1
                request(userId)
            } else Toast.makeText(
                this, "Такой страницы не существует",
                Toast.LENGTH_LONG
            ).show()
        }

        previousPage.setOnClickListener {
            if (userId > 1) {
                userId -= 1
                request(userId)
            } else Toast.makeText(
                this, "Такой страницы не существует",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setupRecycleview() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = characterAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun request(userId: Int) {
        viewModel.getCharacter(userId)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                characterAdapter.seData(response.body()?.results)
            } else {
                Toast.makeText(
                    this, "Ошибка ${response.code().toString()}\n Не найдено",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}