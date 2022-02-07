package com.android.moviecatalogueapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.moviecatalogueapp.data.MovieRepository
import com.android.moviecatalogueapp.data.source.remote.response.MoviesDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsDetailResponse
import com.android.moviecatalogueapp.utils.DataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val moviesDetail = DataDummyTest.generateDummyDetailMovies()
    private val moviesId = moviesDetail.id
    private val tvShowsDetail = DataDummyTest.generateDummyDetailTvShows()
    private val tvShowsId = tvShowsDetail.id

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerDetailMovies: Observer<MoviesDetailResponse?>

    @Mock
    private lateinit var observerDetailTvShows: Observer<TvShowsDetailResponse?>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<MoviesDetailResponse>()
        movies.value = moviesDetail

        `when`(movieRepository.getMoviesDetail(moviesId)).thenReturn(movies)

        val detailMovies = viewModel.getMoviesDetail(moviesId).value
        verify(movieRepository).getMoviesDetail(moviesId)

        assertNotNull(moviesDetail)
        assertEquals(moviesDetail.id, detailMovies?.id)
        assertEquals(moviesDetail.title, detailMovies?.title)
        assertEquals(moviesDetail.overview, detailMovies?.overview)
        assertEquals(moviesDetail.releaseDate, detailMovies?.releaseDate)
        assertEquals(moviesDetail.voteAverage.toInt(), detailMovies?.voteAverage?.toInt())
        assertEquals(moviesDetail.genres, detailMovies?.genres)
        assertEquals(moviesDetail.posterPath, detailMovies?.posterPath)
        assertEquals(moviesDetail.backdropPath, detailMovies?.backdropPath)

        viewModel.getMoviesDetail(moviesId).observeForever(observerDetailMovies)
        verify(observerDetailMovies).onChanged(moviesDetail)
    }

    @Test
    fun getDetailTvShows() {
        val tvShows = MutableLiveData<TvShowsDetailResponse>()
        tvShows.value = tvShowsDetail

        `when`(movieRepository.getTvShowsDetail(tvShowsId)).thenReturn(tvShows)

        val detailTvShows = viewModel.getTvShowsDetail(tvShowsId).value
        verify(movieRepository).getTvShowsDetail(tvShowsId)

        assertNotNull(detailTvShows)
        assertEquals(tvShowsDetail.id, detailTvShows?.id)
        assertEquals(tvShowsDetail.name, detailTvShows?.name)
        assertEquals(tvShowsDetail.overview, detailTvShows?.overview)
        assertEquals(tvShowsDetail.firstAirDate, detailTvShows?.firstAirDate)
        assertEquals(tvShowsDetail.voteAverage.toInt(), detailTvShows?.voteAverage?.toInt())
        assertEquals(tvShowsDetail.genres, detailTvShows?.genres)
        assertEquals(tvShowsDetail.posterPath, detailTvShows?.posterPath)
        assertEquals(tvShowsDetail.backdropPath, detailTvShows?.backdropPath)

        viewModel.getTvShowsDetail(tvShowsId).observeForever(observerDetailTvShows)
        verify(observerDetailTvShows).onChanged(tvShowsDetail)
    }
}