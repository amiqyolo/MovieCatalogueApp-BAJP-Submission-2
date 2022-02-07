package com.android.moviecatalogueapp.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviecatalogueapp.adapter.TvShowsListAdapter
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResultsItem
import com.android.moviecatalogueapp.databinding.FragmentTvShowsBinding
import com.android.moviecatalogueapp.ui.detail.DetailActivity
import com.android.moviecatalogueapp.utils.Constants.EXTRA_MOVIE
import com.android.moviecatalogueapp.utils.Constants.EXTRA_TV_SHOWS_ID
import com.android.moviecatalogueapp.utils.Constants.TV_SHOWS
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding

    private val viewModel: TvShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapter = TvShowsListAdapter()

            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    adapter.submitList(tvShows)
                    showLoading(false)
                }
            })

            with(binding?.rvTvShows) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }

            adapter.setOnItemClickCallBack(object : TvShowsListAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: TvShowsResultsItem) {
                    startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                        putExtra(EXTRA_TV_SHOWS_ID, data.id.toString())
                        putExtra(EXTRA_MOVIE, TV_SHOWS)
                    })
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): TvShowsFragment = TvShowsFragment()
    }
}