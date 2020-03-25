package com.example.rockpapperscissorskotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpapperscissorskotlin.model.Score

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreRoomDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var scoreRoomDatabaseInstance: ScoreRoomDatabase? = null

        fun getDatabase(context: Context): ScoreRoomDatabase? {
            if(scoreRoomDatabaseInstance == null) {
                synchronized(ScoreRoomDatabase::class.java) {
                    if(scoreRoomDatabaseInstance == null) {
                        scoreRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, ScoreRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return scoreRoomDatabaseInstance
        }
    }
}