package com.fangzsx.animu_db.models.youtube

data class Item(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)