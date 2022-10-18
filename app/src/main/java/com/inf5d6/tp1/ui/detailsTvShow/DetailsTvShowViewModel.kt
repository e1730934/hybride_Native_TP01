package com.inf5d6.tp1.ui.detailsTvShow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.DetailsTvShow
import com.inf5d6.tp1.repositories.DetailsTvShowRepository
import com.inf5d6.tp1.repositories.FavoritesRepository
import com.inf5d6.tp1.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsTvShowViewModel(application: Application, tvShowId: Int): AndroidViewModel(application) {
    public val detailsTvShow: MutableLiveData<DetailsTvShow> = MutableLiveData()
    public val isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsTvShowRepository = DetailsTvShowRepository(getApplication())
            detailsTvShowRepository.getTvShowDetails(tvShowId,detailsTvShow)
            detailsTvShowRepository.getFavoriteState(tvShowId,isFavorite)
        }
    }
    fun toggleFavorite(tvShowId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsTvShowRepository = DetailsTvShowRepository(getApplication())
            detailsTvShowRepository.toggleFavorite(tvShowId,isFavorite)
        }
    }


}

