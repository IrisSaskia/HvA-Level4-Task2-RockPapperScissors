package com.example.rockpapperscissorskotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rockpapperscissorskotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var computerMove: Int = 1
    private var playerMove: Int = 1
    private var result: String = "Draw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rock, Paper, Scissors Kotlin"

        initViews()
    }

    private fun initViews() {
        ivRock.setOnClickListener {
            playerMove = 1
            play()
        }
        ivPaper.setOnClickListener {
            playerMove = 2
            play()
        }
        ivScissors.setOnClickListener {
            playerMove = 3
            play()
        }

        updateUI()
    }

    private fun updateUI(){
        tvResult.text = result

        ivComputer.setImageResource(updateImage(computerMove))
        ivPlayer.setImageResource(updateImage(playerMove))
    }

    private fun updateImage(play: Int): Int {
        var imageResource: Int
        if(play == 1) {
            imageResource = R.drawable.rock
        } else if(play == 2) {
            imageResource = R.drawable.paper
        } else {
            imageResource = R.drawable.scissors
        }

        return imageResource
    }

    private fun compareResults() {
        if(playerMove == computerMove) {
            result = getString(R.string.result_draw)
        } else if(playerMove == 2 && computerMove == 1) {
            result = getString(R.string.result_win)
        } else if(playerMove == 3 && computerMove == 1) {
            result = getString(R.string.result_lose)
        } else if(playerMove == 1 && computerMove == 2) {
            result = getString(R.string.result_lose)
        } else if(playerMove == 3 && computerMove == 2) {
            result = getString(R.string.result_win)
        } else if(playerMove == 1 && computerMove == 3) {
            result = getString(R.string.result_win)
        } else if(playerMove == 2 && computerMove == 3) {
            result = getString(R.string.result_lose)
        } else {
            result = getString(R.string.result_draw)
        }
    }

    private fun computerPlay() {
        computerMove = (1..3).random()
    }

    private fun play() {
        computerPlay()
        compareResults()
        updateUI()
    }
}