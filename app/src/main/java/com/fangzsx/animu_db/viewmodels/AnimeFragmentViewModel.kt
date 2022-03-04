package com.fangzsx.animu_db.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.recommendation.RecommendationsResponse
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeFragmentViewModel : ViewModel() {

    var recommendations : MutableLiveData<RecommendationsResponse> = MutableLiveData()

    fun getRecommendations() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                recommendations.postValue(list)
            }
        }
    }



}