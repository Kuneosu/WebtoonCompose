package com.kuneosu.newcompose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.SmallToon

@Database(entities = [BigToon::class, SmallToon::class], version = 1)
abstract class ToonDatabase : RoomDatabase() {
    abstract fun toonDao(): ToonDao

    companion object {
        @Volatile
        private var INSTANCE: ToonDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): ToonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToonDatabase::class.java,
                    "toon-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}