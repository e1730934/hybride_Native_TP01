package com.inf5d6.tp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Season
import com.squareup.picasso.Picasso

class SeasonRVAdapter(private val seasonsList: List<Season>) :
    RecyclerView.Adapter<SeasonRVAdapter.SeasonViewHolder>() {

        class SeasonViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_season, parent, false) as View
            return SeasonViewHolder(view)
        }

        override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
            val imgSeason = holder.view.findViewById<ImageView>(R.id.imgSeasonPoster)
            holder.view.findViewById<TextView>(R.id.txtSeasonName).text = "Season " + seasonsList[position].number.toString()
            holder.view.findViewById<TextView>(R.id.txtEpisodesCount).text = seasonsList[position].episodeCount.toString() + " episodes"
            Picasso.get().load(seasonsList[position].imgURL).into(imgSeason)
        }

    override fun getItemCount(): Int {
        return seasonsList.size
    }

}
