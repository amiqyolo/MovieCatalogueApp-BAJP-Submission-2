@file:Suppress("unused")

package com.android.moviecatalogueapp

import android.app.Application
import com.android.moviecatalogueapp.di.AppModule.remoteModule
import com.android.moviecatalogueapp.di.AppModule.repositoryModule
import com.android.moviecatalogueapp.di.AppModule.retrofitModule
import com.android.moviecatalogueapp.di.AppModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppDelegate)
            modules(
                listOf(
                    retrofitModule,
                    remoteModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}