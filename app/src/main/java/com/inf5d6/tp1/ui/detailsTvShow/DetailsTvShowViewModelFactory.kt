package com.inf5d6.tp1.ui.detailsTvShow

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsTvShowViewModelFactory(val application: Application, private val tvShowId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsTvShowViewModel::class.java)) {
            return DetailsTvShowViewModel(application, tvShowId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
