package com.inf5d6.tp1.ui.detailsTvShow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inf5d6.tp1.R

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
        detailsTvShowViewModelFactory = this.activity?.let { DetailsTvShowViewModelFactory(it.application, tvShowId) }!!
        this.detailsTvShowViewModel =
            ViewModelProvider(this, this.detailsTvShowViewModelFactory)[DetailsTvShowViewModel::class.java]



        this.detailsTvShowViewModel.detailsTvShow.observe(this, {
            val tvShowTitle = view.findViewById<TextView>(R.id.dtvs_tvTitle)
            val tvShowYear = view.findViewById<TextView>(R.id.dtvs_Years)
            val tvShowEpisodesCount = view.findViewById<TextView>(R.id.dtvs_EpisodesCount)
            if (it.size > 0) {
                Toast.makeText(this.context, it.size.toString(), Toast.LENGTH_SHORT).show()
                tvShowTitle.text = it[0].title
                tvShowYear.text = it[0].year.toString()
            }
        })
    }

}
