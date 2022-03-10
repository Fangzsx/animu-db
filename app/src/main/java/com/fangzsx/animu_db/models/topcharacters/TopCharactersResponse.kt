package com.fangzsx.animu_db.models.topcharacters

data class TopCharactersResponse(
    val `data`: List<Data>,
    val pagination: Pagination
)