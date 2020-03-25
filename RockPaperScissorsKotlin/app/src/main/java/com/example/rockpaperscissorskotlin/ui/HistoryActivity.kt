package com.example.rockpaperscissorskotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        setSupportActionBar(toolbarHistory)
        supportActionBar?.title = getString(R.string.history_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

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

    private fun deleteAllHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                scoreRepository.deleteAllScores()
            }
            getScoresFromDatabase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                val resultIntent = Intent()
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
                true
            }
            R.id.action_delete_history -> {
                deleteAllHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
