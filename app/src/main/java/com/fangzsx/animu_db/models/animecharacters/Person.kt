package com.fangzsx.animu_db.models.animecharacters

data class Person(
    val images: ImagesX,
    val mal_id: Int,
    val name: String,
    val url: String
)