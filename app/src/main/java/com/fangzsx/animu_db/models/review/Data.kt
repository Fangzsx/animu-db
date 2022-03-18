package com.fangzsx.animu_db.models.review

data class Data(
    val date: String,
    val entry: Entry,
    val episodes_watched: Int,
    val chapters_read: Int,
    val mal_id: Int,
    val review: String,
    val scores: Scores,
    val type: String,
    val url: String,
    val user: User,
    val votes: Int
)