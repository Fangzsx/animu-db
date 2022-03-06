package com.fangzsx.animu_db.api

import com.fangzsx.animu_db.models.anime.AnimeResponse
import com.fangzsx.animu_db.models.recommendation.Entry
import com.fangzsx.animu_db.models.recommendation.RecommendationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MALapi {

    @GET("v4/recommendations/anime")
    suspend fun getAnimeRecommendation() : Response<RecommendationsResponse>


    @GET("v4/anime/{id}")
    suspend fun getAnimeByID(
        @Path("id")
        id : Int
    ) : Response<AnimeResponse>


}