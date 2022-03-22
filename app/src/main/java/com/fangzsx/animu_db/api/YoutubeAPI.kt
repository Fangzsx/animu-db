package com.fangzsx.animu_db.api

import com.fangzsx.animu_db.models.youtube.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeAPI {

    @GET("v3/search")
    suspend fun searchForTrailer(
        @Query("part") part : String,
        @Query("maxResults") noOfResult : Int,
        @Query("order") orderBy : String,
        @Query("q") query : String,
        @Query("safeSearch") type : String,
        @Query("key") apiKey : String
    ) : Response<YoutubeResponse>
}