package com.example.rockpaperscissorskotlin.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissorskotlin.R
import com.example.rockpapperscissorskotlin.database.ScoreRepository
import com.example.rockpapperscissorskotlin.model.Score
import com.example.rockpapperscissorskotlin.model.ScoreAdapter

import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.content_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private lateinit var  scoreRepository: ScoreRepository
    private var scores = arrayListOf<Score>()
    private val scoreAdapter = ScoreAdapter(scores)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.history_title)

        scoreRepository = ScoreRepository(this)

        initViews()
    }


    private fun initViews() {
        rvScores.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvScores.adapter = scoreAdapter
        rvScores.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getScoresFromDatabase()
    }

    private fun getScoresFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val scores = withContext(Dispatchers.IO) {
                scoreRepository.getAllScores()
            }
            this@HistoryActivity.scores.clear()
            this@HistoryActivity.scores.addAll(scores)
            scoreAdapter.notifyDataSetChanged()
        }
    }
}
