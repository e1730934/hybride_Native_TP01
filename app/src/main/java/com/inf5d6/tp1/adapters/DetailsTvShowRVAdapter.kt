package com.inf5d6.tp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.DetailsTvShow

class DetailsTvShowRVAdapter(private val tvShowDetails: DetailsTvShow) :
    RecyclerView.Adapter<DetailsTvShowRVAdapter.DetailsTvShowViewHolder>() {
    class DetailsTvShowViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsTvShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detailstvshow, parent, false) as View
        return DetailsTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsTvShowViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.dtvs_tvTitle).text = tvShowDetails.title
        holder.view.findViewById<TextView>(R.id.dtvs_Years).text = tvShowDetails.year.toString()
        holder.view.findViewById<TextView>(R.id.dtvs_EpisodesCount).text = tvShowDetails.episodeCount.toString()
        holder.view.findViewById<TextView>(R.id.dtvs_Summary).text = tvShowDetails.plot
    }

    override fun getItemCount(): Int {
        return 1
    }

}

