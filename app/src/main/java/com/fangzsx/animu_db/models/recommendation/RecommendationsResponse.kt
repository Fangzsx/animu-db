package com.fangzsx.animu_db.models.recommendation

data class RecommendationsResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)