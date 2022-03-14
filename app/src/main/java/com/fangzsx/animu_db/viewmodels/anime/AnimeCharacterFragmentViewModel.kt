package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.animecharacters.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeCharacterFragmentViewModel : ViewModel() {

    var characters : MutableLiveData<List<Data?>> = MutableLiveData()

    fun getCharactersByAnimeID(id : Int) = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getCharactersByAnimeID(id)
        if(response.isSuccessful){
            response.body()?.let { charactersResponse ->
                characters.postValue(charactersResponse.data)
            }
        }
    }


}