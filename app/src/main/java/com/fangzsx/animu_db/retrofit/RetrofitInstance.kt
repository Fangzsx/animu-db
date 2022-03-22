package com.fangzsx.animu_db.retrofit

import com.fangzsx.animu_db.api.MALapi
import com.fangzsx.animu_db.api.YoutubeAPI
import com.fangzsx.animu_db.utils.Constants.Companion.MAL_BASE_URL
import com.fangzsx.animu_db.utils.Constants.Companion.YOUTUBE_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val malAPI: MALapi by lazy{
        Retrofit.Builder()
            .baseUrl(MAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MALapi::class.java)

    }

    val youtubeAPI : YoutubeAPI by lazy {
        Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeAPI::class.java)
    }

}