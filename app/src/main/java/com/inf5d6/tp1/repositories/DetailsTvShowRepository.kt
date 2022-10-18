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
import com.inf5d6.tp1.models.DetailsTvShow

class DetailsTvShowRepository(private val application: Application) {
    fun getTvShowDetails(tvShowId: Int, detailsTvShow: MutableLiveData<DetailsTvShow>) {
        val apiURL = MainActivity.SRVURL+ "/tvshow?tvshowId=" + tvShowId.toString()
        val queue = Volley.newRequestQueue(application)
        val r = StringRequest(
            Request.Method.GET, apiURL,
            {
                val tvShowInfo = Gson().fromJson(it, DetailsTvShow::class.java)
                detailsTvShow.postValue(tvShowInfo)
            },
            {
                println("ERREUR: /api/tvshows")
            })
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
}


