package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.rickandmorty.Model.Character

class DetailCharacter : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_character)
        val name: TextView = findViewById(R.id.name)
        var position = intent.getIntExtra("Position", -1)

    }
}