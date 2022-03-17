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


    fun getMangaRecommendation() = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getMangaRecommendation()
        if(response.isSuccessful){
            response.body()?.let{ recommendationsResponse ->
                _recommendations.postValue(recommendationsResponse.data)
            }
        }
    }


}