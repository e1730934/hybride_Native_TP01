package com.inf5d6.tp1.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.adapters.TvShowRVAdapter

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.favoritesViewModel =
            ViewModelProvider(this)[FavoritesViewModel::class.java]
        this.favoritesViewModel.getFavorites()

        val rvTvShows = view.findViewById<RecyclerView>(R.id.rvTvShows)
        rvTvShows.layoutManager= GridLayoutManager(this.context, 2)
        this.favoritesViewModel.tvshows.observe(viewLifecycleOwner) {
            rvTvShows.adapter = TvShowRVAdapter(it)
        }
    }
}
