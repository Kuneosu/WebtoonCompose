package com.kuneosu.newcompose.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToonDao {
    @Query("SELECT * FROM big_toon")
    suspend fun getBigToonList(): List<BigToon>

    @Query("SELECT * FROM small_toon")
    suspend fun getSmallToonList(): List<SmallToon>

    @Query("SELECT title FROM big_toon WHERE title = :input")
    suspend fun getBigToonTitle(input: String): String

    @Query("SELECT title FROM big_toon WHERE title LIKE '%' || :input || '%'")
    suspend fun searchBigToon(input: String): List<String>

    @Query("SELECT title FROM small_toon WHERE title LIKE '%' || :input || '%'")
    suspend fun searchSmallToon(input: String): List<String>

    @Query("SELECT toon_url FROM big_toon WHERE title = :input")
    suspend fun searchBigToonUrl(input: String): String?

    @Query("SELECT toon_url FROM small_toon WHERE title = :input")
    suspend fun searchSmallToonUrl(input: String): String?

    @Insert
    suspend fun insertBigToon(bigToonEntity: BigToon): Long

    @Insert
    suspend fun insertSmallToon(smallToonEntity: SmallToon): Long

    @Delete
    suspend fun deleteBigToon(bigToonEntity: BigToon): Int

    @Delete
    suspend fun deleteSmallToon(smallToonEntity: SmallToon): Int

    @Query("DELETE FROM big_toon")
    suspend fun deleteAllBigToon(): Int

    @Query("DELETE FROM small_toon")
    suspend fun deleteAllSmallToon(): Int

}