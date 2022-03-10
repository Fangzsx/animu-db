package com.fangzsx.animu_db.models.animecharacters

data class Data(
    val character: Character,
    val role: String,
    val voice_actors: List<VoiceActor>
)