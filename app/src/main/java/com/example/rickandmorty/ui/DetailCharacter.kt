package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.Repository.Repository
import com.squareup.picasso.Picasso

class DetailCharacter : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_character)

        val characterNumber = intent.getIntExtra("characterNumber", -1)
        val characterImage: ImageView = findViewById(R.id.characterImage)
        val characterName: TextView = findViewById(R.id.characterName)
        val characterSpecies: TextView = findViewById(R.id.characterSpecies)
        val characterGender: TextView = findViewById(R.id.characterGender)
        val characterStatus: TextView = findViewById(R.id.characterStatus)
        val characterLocation: TextView = findViewById(R.id.characterLocation)
        val characterEpisode: TextView = findViewById(R.id.characterEpisode)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(application, repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDetailCharacter(characterNumber, this)
        viewModel.myResponseDetailCharacter.observe(this) { response ->
            if (response.isSuccessful) {
                characterName.text = "Имя:\n${response.body()?.name}"
                characterSpecies.text = "Раса:\n${response.body()?.species}"
                characterGender.text = "Пол:\n${response.body()?.gender}"
                characterStatus.text = "Статус:\n${response.body()?.status}"
                characterLocation.text = "Последнее местоположение:\n${response.body()?.location?.name}"
                characterEpisode.text = "Количество эпизодов: ${response.body()?.episode?.size}"
                Picasso.get().load(response.body()?.image).into(characterImage)
            }
        }

    }
}