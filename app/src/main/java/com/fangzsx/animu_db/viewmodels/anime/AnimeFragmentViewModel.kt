package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.recommendation.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeFragmentViewModel : ViewModel() {

    val animeRecommendations : MutableLiveData<List<Data>> = MutableLiveData()
    val animePopular : MutableLiveData<List<com.fangzsx.animu_db.models.popular.Data>> = MutableLiveData()
    val topCharacters : MutableLiveData<List<com.fangzsx.animu_db.models.topcharacters.Data>> = MutableLiveData()
    val reviews : MutableLiveData<List<com.fangzsx.animu_db.models.review.Data>> = MutableLiveData()

    private val _anime : MutableLiveData<com.fangzsx.animu_db.models.anime.Data> = MutableLiveData()
    val anime : LiveData<com.fangzsx.animu_db.models.anime.Data> = _anime



    init {
        getAnimeRecommendations()
        getPopularAnime()
        getAnimeReviews()

    }

    private fun getAnimeRecommendations() = viewModelScope.launch{
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                animeRecommendations.postValue(list.data)

            }
        }
    }

    fun getAnimeByID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeByID(id)
        if(response.isSuccessful){
            response.body()?.let { animeResponse ->
               _anime.postValue(animeResponse.data)
            }
        }
    }


    private fun getPopularAnime() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getPopularAnime()
        if(response.isSuccessful){
            response.body()?.let { list ->
                animePopular.postValue(list.data.subList(0,25))
            }
        }
    }

    fun getTopCharacters() = viewModelScope.launch(Dispatchers.IO) {
        val response = RetrofitInstance.malAPI.getTopCharacters()
        if(response.isSuccessful){
            response.body()?.let { list ->
                topCharacters.postValue(list.data.subList(0,10))
            }
        }
    }

    private fun getAnimeReviews() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeReviews()
        if(response.isSuccessful){
            response.body()?.let { list ->
                reviews.postValue(list.data)
            }
        }
    }



}