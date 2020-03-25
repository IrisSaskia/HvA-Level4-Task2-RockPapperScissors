package com.example.rockpapperscissorskotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scoreTable")
data class Score (

    @ColumnInfo(name = "result")
    var scoreResult: String,

    @ColumnInfo(name = "computer")
    var scoreComputerPlay: Int,

    @ColumnInfo(name = "player")
    var scorePlayerPlay: Int,

    @ColumnInfo(name = "date")
    var scoreDate: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)

//steen: 1, papier: 2, schaar: 3