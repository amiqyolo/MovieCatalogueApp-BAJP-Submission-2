package com.android.moviecatalogueapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.android.moviecatalogueapp.R
import com.android.moviecatalogueapp.data.source.remote.response.MoviesDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsDetailResponse
import com.android.moviecatalogueapp.databinding.ActivityDetailBinding
import com.android.moviecatalogueapp.utils.Constants.ELEVATION
import com.android.moviecatalogueapp.utils.Constants.EXTRA_MOVIE
import com.android.moviecatalogueapp.utils.Constants.EXTRA_MOVIES_ID
import com.android.moviecatalogueapp.utils.Constants.EXTRA_TV_SHOWS_ID
import com.android.moviecatalogueapp.utils.Constants.MOVIES
import com.android.moviecatalogueapp.utils.Constants.TV_SHOWS
import com.android.moviecatalogueapp.utils.Constants.mimeType
import com.android.moviecatalogueapp.utils.GlideLoadImage.load
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra(EXTRA_MOVIE)
        val moviesId = intent.getStringExtra(EXTRA_MOVIES_ID)
        val tvShowsId = intent.getStringExtra(EXTRA_TV_SHOWS_ID)

        when (type) {
            MOVIES -> moviesId?.let { id ->
                showLoading(true)
                viewModel.getMoviesDetail(id.toInt()).observe(this, { dataMovies ->
                    if (dataMovies != null) {
                        populateMovie(dataMovies)
                        showLoading(false)
                    }
                })
            }
            TV_SHOWS -> tvShowsId?.let { id ->
                showLoading(true)
                viewModel.getTvShowsDetail(id.toInt()).observe(this, { dataTvShows ->
                    if (dataTvShows != null) {
                        populateTvShows(dataTvShows)
                        showLoading(false)
                    }
                })
            }
        }

        setupToolbar()
    }

    private fun populateTvShows(tvShowsDetailResponse: TvShowsDetailResponse) {
        binding.apply {
            imgDetailPoster.load(tvShowsDetailResponse.getPosterMovieImage())
            imgDetailBackdrop.load(tvShowsDetailResponse.getBackdropMovieImage())

            if (tvShowsDetailResponse.name.isEmpty()) {
                tvDetailTitle.text = resources.getString(R.string.empty_name)
            } else {
                tvDetailTitle.text = tvShowsDetailResponse.name
            }

            if (tvShowsDetailResponse.firstAirDate.isEmpty()) {
                tvDetailRelease.text = resources.getString(R.string.empty_release)
            } else {
                tvDetailRelease.text = tvShowsDetailResponse.firstAirDate
            }

            if (tvShowsDetailResponse.voteAverage != 0.0) {
                tvDetailRating.text = tvShowsDetailResponse.voteAverage.toString()
            } else {
                tvDetailRating.text = resources.getString(R.string.empty_vote)
            }

            if (tvShowsDetailResponse.overview.isEmpty()) {
                tvDetailOverview.text = resources.getString(R.string.empty_overview)
            } else {
                tvDetailOverview.text = tvShowsDetailResponse.overview
            }

            cgDetailGenre.apply {
                for (genre in tvShowsDetailResponse.genres) {
                    val chip = Chip(this@DetailActivity)
                    if (genre.id != 0) {
                        chip.text = genre.name
                    } else {
                        chip.text = resources.getString(R.string.empty_genres)
                    }
                    chip.chipBackgroundColor = getColorStateList(R.color.secondary_light_color)
                    chip.setTextColor(resources.getColor(R.color.white, null))
                    chip.isEnabled = false
                    addView(chip)
                }
            }
        }
    }

    private fun populateMovie(detailMovie: MoviesDetailResponse) {
        binding.apply {
            imgDetailPoster.load(detailMovie.getPosterMovieImage())
            imgDetailBackdrop.load(detailMovie.getBackdropMovieImage())

            if (detailMovie.title.isEmpty()) {
                tvDetailTitle.text = resources.getString(R.string.empty_title)
            } else {
                tvDetailTitle.text = detailMovie.title
            }

            if (detailMovie.releaseDate.isEmpty()) {
                tvDetailRelease.text = resources.getString(R.string.empty_release)
            } else {
                tvDetailRelease.text = detailMovie.releaseDate
            }

            if (detailMovie.voteAverage != 0.0) {
                tvDetailRating.text = detailMovie.voteAverage.toString()
            } else {
                tvDetailRating.text = resources.getString(R.string.empty_vote)
            }

            if (detailMovie.overview.isEmpty()) {
                tvDetailOverview.text = resources.getString(R.string.empty_overview)
            } else {
                tvDetailOverview.text = detailMovie.overview
            }

            cgDetailGenre.apply {
                for (genre in detailMovie.genres) {
                    val chip = Chip(this@DetailActivity)
                    if (genre.id != 0) {
                        chip.text = genre.name
                    } else {
                        chip.text = resources.getString(R.string.empty_genres)
                    }
                    chip.chipBackgroundColor = getColorStateList(R.color.secondary_light_color)
                    chip.setTextColor(resources.getColor(R.color.white, null))
                    chip.isEnabled = false
                    addView(chip)
                }
            }

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = ELEVATION
        supportActionBar?.title = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_on_shared, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.on_shared) {
            ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setChooserTitle(resources.getString(R.string.title_shared))
                .setText(resources.getString(R.string.text_shared))
                .startChooser()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}