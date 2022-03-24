package com.fangzsx.animu_db.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fangzsx.animu_db.models.anime.Data

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnime(data : Data)

    @Delete
    suspend fun deleteAnime(data : Data)

    @Query("SELECT * FROM anime")
    fun getAllAnime() : LiveData<List<Data>>

}