package com.fangzsx.animu_db.models.animereview

data class Scores(
    val animation: Int,
    val character: Int,
    val enjoyment: Int,
    val overall: Int,
    val sound: Int,
    val story: Int
)