package com.inf5d6.tp1.ui.detailsTvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inf5d6.tp1.R

class DetailsTvShowFragment : Fragment() {
    private lateinit var detailsTvShowViewModel: DetailsTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailstvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.detailsTvShowViewModel =
            ViewModelProvider(this)[DetailsTvShowViewModel::class.java]
    }

}
