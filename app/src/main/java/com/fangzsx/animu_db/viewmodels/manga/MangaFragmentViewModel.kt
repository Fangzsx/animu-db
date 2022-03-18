package com.fangzsx.animu_db.viewmodels.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.recommendation.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MangaFragmentViewModel : ViewModel() {

    //encapsulate
    private val _recommendations : MutableLiveData<List<Data>> = MutableLiveData()
    val recommendations : LiveData<List<Data>> = _recommendations

    private val _topManga : MutableLiveData<List<com.fangzsx.animu_db.models.topmanga.Data>> = MutableLiveData()
    val topManga : LiveData<List<com.fangzsx.animu_db.models.topmanga.Data>> = _topManga

    private val _mangaReviews : MutableLiveData<List<com.fangzsx.animu_db.models.review.Data>> = MutableLiveData()
    val mangaReviews : LiveData<List<com.fangzsx.animu_db.models.review.Data>> = _mangaReviews


    init {
        getMangaReviews()
        getMangaRecommendation()
        getTopManga()
    }

    private fun getMangaRecommendation() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getMangaRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ recommendationsResponse ->
                _recommendations.postValue(recommendationsResponse.data)
            }
        }
    }

    private fun getTopManga() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getTopManga()
        if(response.isSuccessful){
            response.body()?.let{ topMangaResponse ->
                _topManga.postValue(topMangaResponse.data)
            }
        }
    }

    private fun getMangaReviews() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getMangaReviews()
        if(response.isSuccessful){
            response.body()?.let { reviewResponse ->
                _mangaReviews.postValue(reviewResponse.data)
            }
        }
    }


}