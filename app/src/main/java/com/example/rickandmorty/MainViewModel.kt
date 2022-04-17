package com.example.rickandmorty


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import com.example.rickandmorty.Repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(app: Application, private val repository: Repository) : AndroidViewModel(app) {
    val myResponse: MutableLiveData<Response<Character>> = MutableLiveData()
    val myResponseDetailCharacter: MutableLiveData<Response<ResultsItem>> = MutableLiveData()

    fun getCharacter(userId: Int, context: Context) {
        if (hasInternetConnections()) {
            viewModelScope.launch {
                val response = repository.getCharacter(userId)
                myResponse.value = response
            }
        } else {
            Toast.makeText(context, "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDetailCharacter(characterNumber: Int, context: Context) {
        if (hasInternetConnections()) {
            viewModelScope.launch {
                val response = repository.getDetailCharacter(characterNumber)
                myResponseDetailCharacter.value = response
            }
        } else {
            Toast.makeText(context, "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hasInternetConnections(): Boolean {
        val connectivityManager =
            getApplication<RickAmdMortyApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capatibilites =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capatibilites.hasTransport(TRANSPORT_WIFI) -> true
                capatibilites.hasTransport(TRANSPORT_CELLULAR) -> true
                capatibilites.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}


