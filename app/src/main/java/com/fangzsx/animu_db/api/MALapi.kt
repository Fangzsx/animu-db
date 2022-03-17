package com.fangzsx.animu_db.api

import com.fangzsx.animu_db.models.anime.AnimeResponse
import com.fangzsx.animu_db.models.animecharacters.AnimeCharactersResponse
import com.fangzsx.animu_db.models.animereview.AnimeReviewResponse
import com.fangzsx.animu_db.models.character.CharacterResponse
import com.fangzsx.animu_db.models.popular.PopularAnimeResponse
import com.fangzsx.animu_db.models.recommendation.RecommendationsResponse
import com.fangzsx.animu_db.models.searchAnime.SearchAnimeResponse
import com.fangzsx.animu_db.models.topcharacters.TopCharactersResponse
import com.fangzsx.animu_db.models.topmanga.TopMangaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MALapi {

    //anime
    @GET("v4/recommendations/anime")
    suspend fun getAnimeRecommendation() : Response<RecommendationsResponse>

    @GET("v4/anime/{id}")
    suspend fun getAnimeByID(
        @Path("id")
        id : Int
    ) : Response<AnimeResponse>

    @GET("v4/anime/{id}/characters")
    suspend fun getCharactersByAnimeID(
        @Path("id")
        id : Int
    ) : Response<AnimeCharactersResponse>

    @GET("v4/top/anime")
    suspend fun getPopularAnime() : Response<PopularAnimeResponse>

    @GET("v4/top/characters")
    suspend fun getTopCharacters() : Response<TopCharactersResponse>

    @GET("v4/characters/{id}")
    suspend fun getCharacterByID(
        @Path("id")
        id : Int
    ) : Response<CharacterResponse>

    @GET("v4/reviews/anime")
    suspend fun getAnimeReviews() : Response<AnimeReviewResponse>

    @GET("v4/anime?")
    suspend fun searchAnimeTitlesByQuery(
        @Query("q")
        title : String
    ) : Response<SearchAnimeResponse>

    //manga
    @GET("v4/recommendations/manga")
    suspend fun getMangaRecommendation() : Response<RecommendationsResponse>

    @GET("v4/top/manga")
    suspend fun getTopManga() : Response<TopMangaResponse>


}