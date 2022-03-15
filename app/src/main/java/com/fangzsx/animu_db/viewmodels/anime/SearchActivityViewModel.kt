package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.searchAnime.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {

    val searchResults : MutableLiveData<List<Data>> = MutableLiveData()

    fun getAnimeTitlesByQuery(query : String) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.searchAnimeTitlesByQuery(query)
        if(response.isSuccessful){
            response.body()?.let { list ->
                searchResults.postValue(list.data)
            }
        }


    }
}