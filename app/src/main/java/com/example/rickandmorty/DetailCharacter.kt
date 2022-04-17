package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Repository.Repository

class DetailCharacter : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_character)

        val repository = Repository()
        val name: TextView = findViewById(R.id.name)
        val position = intent.getIntExtra("Position", -1)

        val viewModelFactory = MainViewModelFactory(repository)
        viewModel=ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDetailCharacter(position)
        viewModel.myResponseDetailCharacter.observe(this, Observer { response ->
            if (response.isSuccessful)
                Toast.makeText(this, response.body()?.name.toString(), Toast.LENGTH_SHORT ).show()
        })

    }
}