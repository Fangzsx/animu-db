package com.fangzsx.animu_db.retrofit

import com.fangzsx.animu_db.api.MALapi
import com.fangzsx.animu_db.utils.Constants.Companion.MAL_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val malAPI by lazy{
        Retrofit.Builder()
            .baseUrl(MAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MALapi::class.java)

    }

}