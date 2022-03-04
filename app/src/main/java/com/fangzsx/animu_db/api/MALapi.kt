package com.fangzsx.animu_db.api

import com.fangzsx.animu_db.models.recommendation.RecommendationsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MALapi {

    @GET("v4/recommendations/anime")
    suspend fun getAnimeRecommendation() : Response<RecommendationsResponse>


}