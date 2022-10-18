package com.inf5d6.tp1.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.DetailsTvShow

class DetailsTvShowRepository(private val application: Application) {
    fun getTvShowDetails(tvShowId: Int, detailsTvShow: MutableLiveData<MutableList<DetailsTvShow>>) {
        val apiURL = MainActivity.SRVURL+ "/tvshows?tvshowId=" + tvShowId.toString()
        val queue = Volley.newRequestQueue(application)
        val r = StringRequest(
            Request.Method.GET, apiURL,
            {
                val tvShowInfo = Gson().fromJson(it, Array<DetailsTvShow>::class.java)
                println("Test213:${tvShowInfo[tvShowId].title}")
                detailsTvShow.postValue(tvShowInfo.toMutableList())
            },
            {
                println("ERREUR: /api/tvshows")
            })
        queue.add(r)
    }
}


