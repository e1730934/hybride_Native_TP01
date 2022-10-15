package com.inf5d6.tp1.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.TvShow

class HomeRepository(private val application: Application) {
    fun getTvShows(tvshows: MutableLiveData<MutableList<TvShow>>) {
        val queue = Volley.newRequestQueue(application)
        val r = StringRequest(
            Request.Method.GET,MainActivity.SRVURL+ "/tvshows",
            {
                val arrayTvShow = Gson().fromJson(it, Array<TvShow>::class.java)
                tvshows.postValue(arrayTvShow.toMutableList())
            },
            {
                println("ERREUR: /api/tvshows")
            })
        queue.add(r)
    }
}
