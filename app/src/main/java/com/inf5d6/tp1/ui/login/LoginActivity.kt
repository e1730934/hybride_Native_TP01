package com.inf5d6.tp1.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Login
import com.inf5d6.tp1.repositories.LoginRepository

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            loginViewModel.username = findViewById<EditText>(R.id.inputUsername).text.toString()
            loginViewModel.password = findViewById<EditText>(R.id.inputPassword).text.toString()
            loginViewModel.login()
        }
    }
}
