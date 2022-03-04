package com.fangzsx.animu_db.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.recommendation.Entry
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeFragmentViewModel : ViewModel() {

    var recommendations : MutableLiveData<List<Entry>> = MutableLiveData()

    init {
        getRecommendations()
    }

    private fun getRecommendations() = viewModelScope.launch{
        val response = RetrofitInstance.malAPI.getAnimeRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ list->
                for(data in list.data){
                    recommendations.value = data.entry
                }
            }
        }
    }



}