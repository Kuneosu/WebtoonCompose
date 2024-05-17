package com.kuneosu.newcompose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.SmallToon

@Database(entities = [BigToon::class, SmallToon::class], version = 3)
abstract class ToonDatabase : RoomDatabase() {
    abstract fun toonDao(): ToonDao

    companion object {
        @Volatile
        private var INSTANCE: ToonDatabase? = null

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 1. 새로운 구조를 가진 임시 테이블 생성
                database.execSQL(
                    """
            CREATE TABLE big_toon_temp (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                main_image TEXT,  -- 데이터 타입을 String?으로 변경
                title_image TEXT NOT NULL,  -- 데이터 타입을 String?으로 변경
                sub_title TEXT,
                background_image TEXT NOT NULL,  -- 데이터 타입을 String?으로 변경
                main_gif TEXT,  -- 데이터 타입을 String?으로 변경
                toon_url TEXT NOT NULL
            )
        """
                )

                // 2. 기존 테이블의 데이터를 새 테이블로 복사
                database.execSQL(
                    """
            INSERT INTO big_toon_temp (id, title, main_image, title_image, sub_title, background_image, main_gif, toon_url)
            SELECT id, title, main_image, title_image, sub_title, background_image, main_gif, toon_url FROM big_toon
        """
                )

                // 3. 기존 테이블 삭제
                database.execSQL("DROP TABLE big_toon")

                // 4. 임시 테이블의 이름을 기존 테이블의 이름으로 변경
                database.execSQL("ALTER TABLE big_toon_temp RENAME TO big_toon")
            }
        }

        @Synchronized
        fun getDatabase(context: Context): ToonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToonDatabase::class.java,
                    "toon-database"
                ).addMigrations(MIGRATION_2_3).build()
                INSTANCE = instance
                instance
            }
        }
    }
}