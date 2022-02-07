package com.android.moviecatalogueapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.moviecatalogueapp.data.MovieRepository
import com.android.moviecatalogueapp.data.source.remote.response.MoviesResultsItem
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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    private val moviesResponse = DataDummyTest.generateDummyMovie()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovies: Observer<List<MoviesResultsItem>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val listMovies = MutableLiveData<List<MoviesResultsItem>>()
        listMovies.value = moviesResponse

        `when`(movieRepository.getMovies()).thenReturn(listMovies)

        val moviesValue = viewModel.getMovies().value
        verify(movieRepository).getMovies()

        assertNotNull(moviesValue)
        assertEquals(3, moviesValue?.size)

        viewModel.getMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(moviesResponse)
    }

}