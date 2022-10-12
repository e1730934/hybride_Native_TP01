package com.inf5d6.tp1.repositories

import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.Login
import org.json.JSONObject

class LoginRepository(private val application: Application) {

    fun getBearerToken(loginInfo: Login) {
        val queue = Volley.newRequestQueue(application)
        val jsonBody = JSONObject()
        jsonBody.put("username", loginInfo.username)
        jsonBody.put("password", loginInfo.password)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, MainActivity.SRVURL+ "/auth/token", jsonBody,
            { response ->
                if (response != null) {
                    if(response.has("token")){
                        MainActivity.TOKEN.postValue("Bearer " + response.getString("token"))

                        val intent = Intent(application, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        application.startActivity(intent)
                    }
                }
            },
            { error ->
                if (error != null) {
                    Toast.makeText(application, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            })
        queue.add(jsonObjectRequest)
    }
}
