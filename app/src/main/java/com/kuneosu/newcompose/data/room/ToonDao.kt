package com.kuneosu.newcompose.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.SmallToon

@Dao
interface ToonDao {
    @Query("SELECT * FROM big_toon")
    fun getBigToonList(): List<BigToon>

    @Query("SELECT * FROM small_toon")
    fun getSmallToonList(): List<SmallToon>

    @Query("SELECT title FROM big_toon WHERE title = :input")
    fun getBigToonTitle(input: String): String

    // Search for a BigToon by title like
    @Query("SELECT title FROM big_toon WHERE title LIKE '%' || :input || '%'")
    fun searchBigToon(input: String): List<String>

    // Search for a SmallToon by title like
    @Query("SELECT title FROM small_toon WHERE title LIKE '%' || :input || '%'")
    fun searchSmallToon(input: String): List<String>

    @Insert
    fun insertBigToon(bigToonEntity: BigToon)

    @Insert
    fun insertSmallToon(smallToonEntity: SmallToon)

    @Delete
    fun deleteBigToon(bigToonEntity: BigToon)

    @Delete
    fun deleteSmallToon(smallToonEntity: SmallToon)

    @Query("DELETE FROM big_toon")
    fun deleteAllBigToon()

    @Query("DELETE FROM small_toon")
    fun deleteAllSmallToon()


}