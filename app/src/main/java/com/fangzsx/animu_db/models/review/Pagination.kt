package com.fangzsx.animu_db.models.review

data class Pagination(
    val has_next_page: Boolean,
    val last_visible_page: Int
)