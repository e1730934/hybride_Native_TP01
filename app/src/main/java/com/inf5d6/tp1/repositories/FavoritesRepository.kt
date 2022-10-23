package com.inf5d6.tp1.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
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
                tvshows.value = arrayTvShow.toMutableList()
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
    fun getFavoriteState(tvShowId: Int, favoriteState: MutableLiveData<Boolean>) {
        val apiURL = MainActivity.SRVURL+ "/favorite?tvshowId=" + tvShowId.toString()
        val queue = Volley.newRequestQueue(application)
        val r = object : JsonObjectRequest(
            Request.Method.GET, apiURL, null,
            {
                favoriteState.value = it.getBoolean("isFavorite")
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

    fun toggleFavorite(tvShowId: Int, isFavorite: MutableLiveData<Boolean>){
        val apiURL = MainActivity.SRVURL+ "/favorite?tvshowId=" + tvShowId.toString()
        val method = if (isFavorite.value == true) Request.Method.DELETE else Request.Method.POST
        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            method, apiURL,
            {
                // les méthodes DELTETE et POST ne renvoient pas de réponse
                isFavorite.value = !isFavorite.value!!
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
