package com.example.rickandmorty.Repository

import com.example.rickandmorty.API.RetrofitInstance
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import retrofit2.Response

class Repository {
    suspend fun getCharacter(userId: Int): Response<MutableList<ResultsItem>> {
        return RetrofitInstance.api.getCharachter(userId)
    }
}