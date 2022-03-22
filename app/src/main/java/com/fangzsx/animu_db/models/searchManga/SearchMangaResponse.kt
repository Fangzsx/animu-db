package com.fangzsx.animu_db.models.searchManga

data class SearchMangaResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)