package com.android.moviecatalogueapp.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.moviecatalogueapp.data.MovieRepository
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResultsItem
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
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    private val tvShowsResponse = DataDummyTest.generateDummyTvShows()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerTvShowsResultsItem: Observer<List<TvShowsResultsItem>>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val listTvShows = MutableLiveData<List<TvShowsResultsItem>>()
        listTvShows.value = tvShowsResponse

        `when`(movieRepository.getTvShows()).thenReturn(listTvShows)

        val tvShowsValue = viewModel.getTvShows().value
        verify(movieRepository).getTvShows()

        assertNotNull(tvShowsValue)
        assertEquals(3, tvShowsValue?.size)

        viewModel.getTvShows().observeForever(observerTvShowsResultsItem)
        verify(observerTvShowsResultsItem).onChanged(tvShowsResponse)
    }

}