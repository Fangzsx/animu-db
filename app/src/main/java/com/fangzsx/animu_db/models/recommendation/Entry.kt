package com.fangzsx.animu_db.models.recommendation

data class Entry(
    val images: Images,
    val mal_id: Int,
    val title: String,
    val url: String
)