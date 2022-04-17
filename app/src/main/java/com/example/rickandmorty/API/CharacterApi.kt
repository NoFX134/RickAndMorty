package com.example.rickandmorty.API

import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")

    suspend fun getCharacter(@Query("page") page:Int): Response<Character>

    @GET("character/{characterNumber}")
    suspend fun getDetailCharacter (@Path("characterNumber") characterNumber: Int): Response<ResultsItem>
}