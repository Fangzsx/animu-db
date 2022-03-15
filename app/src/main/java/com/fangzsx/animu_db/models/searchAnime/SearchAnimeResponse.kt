package com.fangzsx.animu_db.models.searchAnime

data class SearchAnimeResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)