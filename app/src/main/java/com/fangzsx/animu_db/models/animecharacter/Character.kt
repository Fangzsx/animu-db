package com.fangzsx.animu_db.models.animecharacter

data class Character(
    val images: Images,
    val mal_id: Int,
    val name: String,
    val url: String
)