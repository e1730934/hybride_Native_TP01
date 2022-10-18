package com.inf5d6.tp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Role
import com.squareup.picasso.Picasso

class CastRVAdapter(private val castMembers: List<Role>) :
    RecyclerView.Adapter<CastRVAdapter.CastViewModel>() {

    class CastViewModel(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast, parent, false) as View
        return CastViewModel(view)
    }

    override fun onBindViewHolder(holder: CastViewModel, position: Int) {
        val imgCast = holder.view.findViewById<ImageView>(R.id.imgSeasonPoster)
        holder.view.findViewById<TextView>(R.id.txtCharacterName).text = castMembers[position].character
        holder.view.findViewById<TextView>(R.id.txtCastFullName).text = castMembers[position].name
        Picasso.get().load(castMembers[position].imgURL).into(imgCast)
    }

    override fun getItemCount(): Int {
        return castMembers.size
    }
}
