package com.inf5d6.tp1.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.TvShow

class FavoritesRepository(private val application: Application) {
    fun getFavoriteTvShows(tvshows: MutableLiveData<MutableList<TvShow>>) {
        val queue = Volley.newRequestQueue(application)
        val r = object : JsonArrayRequest(
            Request.Method.GET, MainActivity.SRVURL + "/favorites", null,
            {
                val arrayTvShow = Gson().fromJson(it.toString(), Array<TvShow>::class.java)
                tvshows.postValue(arrayTvShow.toMutableList())
            },
            {
                println("ERREUR: /api/favorites")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = MainActivity.TOKEN.value.toString()
                return headers
            }
        }
        queue.add(r)
    }
}
