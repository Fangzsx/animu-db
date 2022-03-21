package com.fangzsx.animu_db.viewmodels.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.animecharacters.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MangaCharFragmentViewModel : ViewModel() {

    private val _characters : MutableLiveData<List<Data>> = MutableLiveData()
    val characters : LiveData<List<Data>> = _characters

    fun getMangaCharactersByID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getMangaCharactersByID(id)
        if(response.isSuccessful){
            response.body()?.let {
                _characters.postValue(it.data.filter { char -> char.role.lowercase() == "main" })
            }
        }
    }

}