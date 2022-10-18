package com.inf5d6.tp1.ui.detailsTvShow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.DetailsTvShow
import com.inf5d6.tp1.repositories.DetailsTvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsTvShowViewModel(application: Application, tvShowId: Int): AndroidViewModel(application) {
    public val detailsTvShow: MutableLiveData<MutableList<DetailsTvShow>> = MutableLiveData(mutableListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsTvShowRepository = DetailsTvShowRepository(getApplication())
            detailsTvShowRepository.getTvShowDetails(tvShowId,detailsTvShow)
        }
    }


}

