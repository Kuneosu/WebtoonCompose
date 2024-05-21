package com.kuneosu.newcompose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.SmallToon

@Database(entities = [BigToon::class, SmallToon::class], version = 4)
abstract class ToonDatabase : RoomDatabase() {
    abstract fun toonDao(): ToonDao

    companion object {
        @Volatile
        private var INSTANCE: ToonDatabase? = null

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 1. 새로운 구조를 가진 임시 테이블 생성
                database.execSQL(
                    """
            CREATE TABLE small_toon_temp (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                main_image TEXT NOT NULL,  -- 데이터 타입을 String?으로 변경
                toon_url TEXT NOT NULL
            )
        """
                )

                // 2. 기존 테이블의 데이터를 새 테이블로 복사
                database.execSQL(
                    """
            INSERT INTO small_toon_temp (id, title, main_image, toon_url)
            SELECT id, title, main_image, toon_url FROM small_toon
        """
                )

                // 3. 기존 테이블 삭제
                database.execSQL("DROP TABLE small_toon")

                // 4. 임시 테이블의 이름을 기존 테이블의 이름으로 변경
                database.execSQL("ALTER TABLE small_toon_temp RENAME TO small_toon")
            }
        }

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