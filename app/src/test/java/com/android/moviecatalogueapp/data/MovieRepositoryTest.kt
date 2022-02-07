package com.android.moviecatalogueapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.moviecatalogueapp.data.source.remote.RemoteDataSource
import com.android.moviecatalogueapp.utils.DataDummyTest
import com.android.moviecatalogueapp.utils.LiveDataTestUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val moviesResponse = DataDummyTest.generateDummyMovie()
    private val tvShowsResponse = DataDummyTest.generateDummyTvShows()

    private val moviesDetail = DataDummyTest.generateDummyDetailMovies()
    private val moviesId = moviesDetail.id
    private val tvShowsDetail = DataDummyTest.generateDummyDetailTvShows()
    private val tvShowsId = tvShowsDetail.id

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val moviesValue = LiveDataTestUtils.getValue(movieRepository.getMovies())

        verify(remote).getMovies(any())

        assertNotNull(moviesValue)
        assertEquals(moviesResponse.size, moviesValue.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMoviesCallback).onDetailMoviesReceived(
                moviesDetail
            )
            null
        }.`when`(remote).getDetailMovies(eq(moviesId), any())

        val detailMoviesValue =
            LiveDataTestUtils.getValue(movieRepository.getMoviesDetail(moviesId))

        verify(remote).getDetailMovies(eq(moviesId), any())

        assertNotNull(detailMoviesValue)
        assertEquals(moviesDetail.id, detailMoviesValue.id)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowsReceived(
                tvShowsResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowsValue = LiveDataTestUtils.getValue(movieRepository.getTvShows())

        verify(remote).getTvShows(any())

        assertNotNull(tvShowsValue)
        assertEquals(tvShowsResponse.size, tvShowsValue.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowsCallback).onDetailTvShowsReceived(
                tvShowsDetail
            )
            null
        }.`when`(remote).getDetailTvShows(eq(tvShowsId), any())

        val detailTvShowsValue =
            LiveDataTestUtils.getValue(movieRepository.getTvShowsDetail(tvShowsId))

        verify(remote).getDetailTvShows(eq(tvShowsId), any())

        assertNotNull(detailTvShowsValue)
        assertEquals(tvShowsDetail.id, detailTvShowsValue.id)
    }

}