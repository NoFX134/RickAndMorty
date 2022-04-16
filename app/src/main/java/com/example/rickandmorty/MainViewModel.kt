package com.example.rickandmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import com.example.rickandmorty.Repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<Character>> = MutableLiveData()

    fun getCharacter(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacter(userId)
            myResponse.value = response
        }

    }
}


