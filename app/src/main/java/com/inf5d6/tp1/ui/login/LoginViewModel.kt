package com.inf5d6.tp1.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.Login
import com.inf5d6.tp1.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application):AndroidViewModel(application) {
    var username = ""
    var password = ""
    val loginRepository = LoginRepository(application)

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val loginInfo = Login(username.toString(), password.toString())
            loginRepository.getBearerToken(loginInfo)
        }

    }

}
