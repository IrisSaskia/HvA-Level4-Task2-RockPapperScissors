package com.example.rockpapperscissorskotlin.database

import androidx.room.*
import com.example.rockpapperscissorskotlin.model.Score

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scoreTable")
    suspend fun getAllScores(): List<Score>

    @Insert
    suspend fun insertScore(score: Score)

    @Delete
    suspend fun deleteScore(score: Score)

    @Update
    suspend fun updateScore(score: Score)

    @Query("DELETE FROM scoreTable")
    suspend fun deleteAllScores()
}