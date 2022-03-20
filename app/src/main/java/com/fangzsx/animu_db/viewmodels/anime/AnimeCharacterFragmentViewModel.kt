package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.animecharacters.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeCharacterFragmentViewModel : ViewModel() {

    var characters : MutableLiveData<List<Data?>> = MutableLiveData()

    private val _character : MutableLiveData<com.fangzsx.animu_db.models.character.Data> = MutableLiveData()
    val character : LiveData<com.fangzsx.animu_db.models.character.Data> = _character

    fun getCharactersByAnimeID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getCharactersByAnimeID(id)
        if(response.isSuccessful){
            response.body()?.let { charactersResponse ->
                characters.postValue(charactersResponse.data)
            }
        }
    }

    fun getAnimeCharacterInfoByID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getCharacterByID(id)
        if(response.isSuccessful){
            response.body()?.let { charResponse ->
                _character.postValue(charResponse.data)
            }
        }
    }


}