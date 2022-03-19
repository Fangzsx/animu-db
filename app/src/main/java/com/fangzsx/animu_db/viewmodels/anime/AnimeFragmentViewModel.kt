package com.fangzsx.animu_db.viewmodels.anime

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



    init {
        getAnimeRecommendations()
        getPopularAnime()
        getAnimeReviews()

    }

    private fun getAnimeRecommendations() = viewModelScope.launch{
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                animeRecommendations.postValue(list.data.subList(0,10))

            }
        }
    }


    private fun getPopularAnime() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getPopularAnime()
        if(response.isSuccessful){
            response.body()?.let { list ->
                animePopular.postValue(list.data.subList(0,10))
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