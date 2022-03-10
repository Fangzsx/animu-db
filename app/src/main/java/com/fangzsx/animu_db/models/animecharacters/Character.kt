package com.fangzsx.animu_db.models.animecharacters

data class Character(
    val images: Images,
    val mal_id: Int,
    val name: String,
    val url: String
)