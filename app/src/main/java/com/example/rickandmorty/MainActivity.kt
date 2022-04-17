package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Repository.Repository
import com.example.rickandmorty.adapter.CharacterAdapter


class MainActivity : AppCompatActivity(), CharacterAdapter.ItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var nextPage: Button
    private lateinit var previousPage: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private var userId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        characterAdapter = CharacterAdapter(this)
        nextPage = findViewById(R.id.nextPage)
        previousPage = findViewById(R.id.previousPage)
        setupRecycleview()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        request(userId)


        nextPage.setOnClickListener {
            if (userId < 42) {
                userId += 1
                request(userId)
                recyclerView.scrollToPosition(0)
            } else Toast.makeText(
                this, "Такой страницы не существует",
                Toast.LENGTH_SHORT
            ).show()
        }

        previousPage.setOnClickListener {
            if (userId > 1) {
                userId -= 1
                request(userId)
                recyclerView.scrollToPosition(0)
            } else Toast.makeText(
                this, "Такой страницы не существует",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailCharacter::class.java)
           intent.putExtra("characterNumber", (position+20*userId-19))
            startActivity(intent)
    }


    private fun setupRecycleview() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = characterAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun request(userId: Int) {
        viewModel.getCharacter(userId, this)
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                characterAdapter.seData(
                    response.body()?.results
                )
            } else {

                Toast.makeText(
                    this, "Ошибка ${response.code().toString()}\n Не найдено",
                    Toast.LENGTH_LONG
                ).show()
           }
        }
    }

}