package com.fangzsx.animu_db.viewmodels.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.searchManga.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MangaSearchActivityViewModel : ViewModel() {

    private val _titles : MutableLiveData<List<Data>> = MutableLiveData()
    val titles : LiveData<List<Data>> = _titles

    fun searchMangaTitlesByQuery(query : String) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.searchMangaTitlesByQuery(query)
        if(response.isSuccessful){
            response.body()?.let { mangaTitles ->
                _titles.postValue(mangaTitles.data.sortedBy { it.popularity })
            }
        }
    }
}