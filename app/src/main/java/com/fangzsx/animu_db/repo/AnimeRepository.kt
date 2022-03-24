package com.fangzsx.animu_db.repo

import com.fangzsx.animu_db.db.AnimeDao
import com.fangzsx.animu_db.models.anime.Data

class AnimeRepository(
    private val animeDao : AnimeDao
) {
    val allAnime = animeDao.getAllAnime()

    suspend fun addAnime(data : Data){
        animeDao.addAnime(data)
    }

    suspend fun deleteAnime(data : Data){
        animeDao.deleteAnime(data)
    }

}