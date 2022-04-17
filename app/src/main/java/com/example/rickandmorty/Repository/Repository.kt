package com.example.rickandmorty.Repository

import com.example.rickandmorty.API.RetrofitInstance
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import retrofit2.Response
import retrofit2.http.Path

class Repository {
    suspend fun getCharacter(userId: Int): Response<Character> {
        return RetrofitInstance.api.getCharacter(userId)
    }
    suspend fun getDetailCharacter ( characterNumber: Int): Response<ResultsItem>{
        return RetrofitInstance.api.getDetailCharacter (characterNumber)
    }
}