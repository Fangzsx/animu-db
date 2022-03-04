package com.fangzsx.animu_db.models.recommendation

data class Pagination(
    val has_next_page: Boolean,
    val last_visible_page: Int
)