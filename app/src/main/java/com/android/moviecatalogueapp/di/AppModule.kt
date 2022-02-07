package com.android.moviecatalogueapp.di

import com.android.moviecatalogueapp.data.MovieRepository
import com.android.moviecatalogueapp.data.source.remote.RemoteDataSource
import com.android.moviecatalogueapp.data.source.remote.retrofit.ApiConfig
import com.android.moviecatalogueapp.ui.detail.DetailViewModel
import com.android.moviecatalogueapp.ui.movies.MoviesViewModel
import com.android.moviecatalogueapp.ui.tvshows.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val retrofitModule = module {
        single { ApiConfig.getApiService() }
    }

    val remoteModule = module {
        factory { RemoteDataSource(get()) }
    }

    val repositoryModule = module {
        factory { MovieRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { TvShowsViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

}