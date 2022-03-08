package com.fangzsx.animu_db.models.animecharacter

data class Data(
    val character: Character,
    val role: String,
    val voice_actors: List<VoiceActor>
)