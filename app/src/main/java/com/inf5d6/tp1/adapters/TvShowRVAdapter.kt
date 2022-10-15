package com.inf5d6.tp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.TvShow
import com.squareup.picasso.Picasso

class TvShowRVAdapter(private val listTvShow: MutableList<TvShow>)
    :RecyclerView.Adapter<TvShowRVAdapter.TvShowViewHolder>() {

        class TvShowViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tvshow, parent, false) as View
            return TvShowViewHolder(view)
        }

        override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
            val imgTvShow = holder.view.findViewById<ImageView>(R.id.imgTvShow)
            Picasso.get().load(listTvShow[position].imgURL).into(imgTvShow)
        }

        override fun getItemCount() = this.listTvShow.size

}
