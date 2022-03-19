package com.fangzsx.animu_db.viewmodels.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.manga.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MangaActivityViewModel : ViewModel() {

    private val _manga : MutableLiveData<Data> = MutableLiveData()
    val manga : LiveData<Data> = _manga

    fun getMangaByID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getMangaByID(id)
        if(response.isSuccessful){
            response.body()?.let { manga ->
                _manga.postValue(manga.data)
            }
        }
    }
}