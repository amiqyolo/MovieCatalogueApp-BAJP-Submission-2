package com.android.moviecatalogueapp.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviecatalogueapp.adapter.MovieListAdapter
import com.android.moviecatalogueapp.data.source.remote.response.MoviesResultsItem
import com.android.moviecatalogueapp.databinding.FragmentMoviesBinding
import com.android.moviecatalogueapp.ui.detail.DetailActivity
import com.android.moviecatalogueapp.utils.Constants.EXTRA_MOVIE
import com.android.moviecatalogueapp.utils.Constants.EXTRA_MOVIES_ID
import com.android.moviecatalogueapp.utils.Constants.MOVIES
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding

    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapter = MovieListAdapter()

            showLoading(true)
            viewModel.getMovies().observe(viewLifecycleOwner, { result ->
                if (result != null) {
                    adapter.submitList(result)
                    showLoading(false)
                }
            })

            with(binding?.rvMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }

            adapter.setOnItemClickCallBack(object : MovieListAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: MoviesResultsItem) {
                    startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                        putExtra(EXTRA_MOVIES_ID, data.id.toString())
                        putExtra(EXTRA_MOVIE, MOVIES)
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
        fun newInstance(): MoviesFragment = MoviesFragment()
    }
}