package com.example.rockpaperscissorskotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissorskotlin.R
import com.example.rockpapperscissorskotlin.database.ScoreRepository
import com.example.rockpapperscissorskotlin.model.Score

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val ADD_HISTORY_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private var computerMove: Int = 1
    private var playerMove: Int = 1
    private var result: String = "Draw"
    private lateinit var scoreRepository: ScoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        scoreRepository = ScoreRepository(this)

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
        addToDatabase()
    }

    private fun startHistoryActivity() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivityForResult(intent, ADD_HISTORY_REQUEST_CODE)
    }

    private fun addToDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val score = Score(result, computerMove, playerMove, Date().toString())
            withContext(Dispatchers.IO){
                scoreRepository.insertScore(score)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_go_to_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
