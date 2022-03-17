package com.fangzsx.animu_db.models.topmanga

data class TopMangaResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)