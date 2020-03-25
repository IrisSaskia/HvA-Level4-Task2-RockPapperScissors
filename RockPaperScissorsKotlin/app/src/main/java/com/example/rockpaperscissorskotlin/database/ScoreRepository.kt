package com.example.rockpapperscissorskotlin.database

import android.content.Context
import com.example.rockpapperscissorskotlin.model.Score

class ScoreRepository(context: Context) {
    private var scoreDao: ScoreDao

    init {
        val scoreRoomDatabase = ScoreRoomDatabase.getDatabase(context)
        scoreDao = scoreRoomDatabase!!.scoreDao()
    }

    suspend fun getAllScores(): List<Score> {
        return scoreDao.getAllScores()
    }

    suspend fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    suspend fun deleteScore(score: Score) {
        scoreDao.deleteScore(score)
    }

    suspend fun updateScore(score: Score) {
        scoreDao.updateScore(score)
    }

    suspend fun deleteAllScores() {
        scoreDao.deleteAllScores()
    }
}