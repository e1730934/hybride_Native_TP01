package com.inf5d6.tp1.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.TvShow
import com.inf5d6.tp1.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(val app: Application) : AndroidViewModel(app) {
    public val tvshows: MutableLiveData<MutableList<TvShow>> = MutableLiveData(mutableListOf())
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesRepository = FavoritesRepository(app)
            favoritesRepository.getFavoriteTvShows(tvshows)
        }
    }


}
