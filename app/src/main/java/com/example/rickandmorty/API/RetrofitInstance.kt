package com.example.rickandmorty.API

import com.example.rickandmorty.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: CharacterApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }
}