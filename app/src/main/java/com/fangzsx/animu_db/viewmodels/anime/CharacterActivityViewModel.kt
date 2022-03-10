package com.fangzsx.animu_db.viewmodels.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.animu_db.models.character.CharacterResponse
import com.fangzsx.animu_db.models.character.Data
import com.fangzsx.animu_db.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class CharacterActivityViewModel : ViewModel() {

    val character : MutableLiveData<Data> = MutableLiveData()

    fun getCharacterByID(id : Int)  = viewModelScope.launch {
        val response = RetrofitInstance.malAPI.getCharacterByID(id)
        if(response.isSuccessful){
            response.body()?.let{ charResponse ->
                character.postValue(charResponse.data)
            }
        }
    }
}