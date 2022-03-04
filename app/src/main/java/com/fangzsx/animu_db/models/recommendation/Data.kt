package com.fangzsx.animu_db.models.recommendation

data class Data(
    val content: String,
    val date: String,
    val entry: List<Entry>,
    val mal_id: String,
    val user: User
)