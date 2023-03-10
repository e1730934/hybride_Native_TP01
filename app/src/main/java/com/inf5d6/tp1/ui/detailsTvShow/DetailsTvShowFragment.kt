package com.inf5d6.tp1.ui.detailsTvShow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.adapters.CastRVAdapter
import com.inf5d6.tp1.adapters.SeasonRVAdapter
import com.inf5d6.tp1.models.Role
import com.squareup.picasso.Picasso

class DetailsTvShowFragment : Fragment() {
    private lateinit var detailsTvShowViewModel: DetailsTvShowViewModel
    private lateinit var detailsTvShowViewModelFactory: DetailsTvShowViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailstvshow, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet", "FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowId = this.requireArguments().getInt("tvShowId")

        val tvShowTitle = view.findViewById<TextView>(R.id.dtvs_tvTitle)
        val tvShowYear = view.findViewById<TextView>(R.id.dtvs_Years)
        val tvShowEpisodesCount = view.findViewById<TextView>(R.id.dtvs_EpisodesCount)
        val tvShowSummary = view.findViewById<TextView>(R.id.dtvs_Summary)
        val tvShowImg = view.findViewById<ImageView>(R.id.dtvs_imgTvShow)
        val tvShowRvCast = view.findViewById<RecyclerView>(R.id.dtvs_rvCast)
        val tvShowRvSeasons = view.findViewById<RecyclerView>(R.id.dtvs_rvSeasons)

        val tvShowFavBtn = view.findViewById<ImageButton>(R.id.dtvs_btnActionFav)

        val layoutManagerCast = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerSeason = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        tvShowRvCast.layoutManager = layoutManagerCast
        tvShowRvSeasons.layoutManager = layoutManagerSeason

        tvShowFavBtn.setOnClickListener {
            detailsTvShowViewModel.toggleFavorite(tvShowId)
        }

        detailsTvShowViewModelFactory =
            DetailsTvShowViewModelFactory(activity!!.application, tvShowId)
        this.detailsTvShowViewModel =
            ViewModelProvider(this, this.detailsTvShowViewModelFactory)[DetailsTvShowViewModel::class.java]

        this.detailsTvShowViewModel.isFavorite.observe(this) {
            if (it == true) {
                tvShowFavBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                tvShowFavBtn.setImageResource(R.drawable.ic_baseline_favorite_24_white)
            }
        }

        this.detailsTvShowViewModel.detailsTvShow.observe(this) {
            if (it != null) {
                tvShowTitle.text = it.title
                tvShowYear.text = it.year.toString()
                tvShowEpisodesCount.text = it.episodeCount.toString() + " episodes"
                tvShowSummary.text = it.plot
                Picasso.get().load(it.imgURL).into(tvShowImg)

                if (it.roles != null) {
                    val castAdapter = CastRVAdapter(it.roles)
                    tvShowRvCast.adapter = castAdapter
                }
                if(it.seasons != null){
                    val seasonsAdapter = SeasonRVAdapter(it.seasons)
                    tvShowRvSeasons.adapter = seasonsAdapter
                }
            } else {
                Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
