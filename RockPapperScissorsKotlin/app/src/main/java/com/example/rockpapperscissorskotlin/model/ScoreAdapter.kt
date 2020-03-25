package com.example.rockpapperscissorskotlin.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpapperscissorskotlin.R
import kotlinx.android.synthetic.main.item_score.view.*

class ScoreAdapter(private val scores: List<Score>) :
    RecyclerView.Adapter<ScoreAdapter.ViewHolder>(){

    /**
     * Creates and returns a ViewHolder object, inflating the layout called item_score.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        )
    }
    /* Returns the size of the list*/

    override fun getItemCount(): Int {
        return scores.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scores[position])
    }




    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(score: Score){
            itemView.tvHistoryResult.text = score.scoreResult
            itemView.tvHistoryDate.text = score.scoreDate
            when(score.scoreComputerPlay) {
                1 -> itemView.ivComputerHistory.setImageResource(R.drawable.rock)
                2 -> itemView.ivComputerHistory.setImageResource(R.drawable.paper)
                3 -> itemView.ivComputerHistory.setImageResource(R.drawable.scissors)
                else -> itemView.ivComputerHistory.setImageResource(R.drawable.ic_delete_white_24dp)
            }
            when(score.scorePlayerPlay) {
                1 -> itemView.ivPlayerHistory.setImageResource(R.drawable.rock)
                2 -> itemView.ivPlayerHistory.setImageResource(R.drawable.paper)
                3 -> itemView.ivPlayerHistory.setImageResource(R.drawable.scissors)
                else -> itemView.ivPlayerHistory.setImageResource(R.drawable.ic_delete_white_24dp)
            }
        }
    }
}