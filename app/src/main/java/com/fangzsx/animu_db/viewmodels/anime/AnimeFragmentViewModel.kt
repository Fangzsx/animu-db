package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.recommendation.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeFragmentViewModel : ViewModel() {

    var recommendations : MutableLiveData<List<Data>> = MutableLiveData()

    init {
        getRecommendations()
    }

    private fun getRecommendations() = viewModelScope.launch{
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                recommendations.postValue(list.data)

            }
        }
    }



}