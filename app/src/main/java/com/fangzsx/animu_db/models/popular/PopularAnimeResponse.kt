package com.fangzsx.animu_db.models.popular

data class PopularAnimeResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)