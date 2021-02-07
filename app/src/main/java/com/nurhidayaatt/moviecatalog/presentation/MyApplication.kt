package com.nurhidayaatt.moviecatalog.presentation

import android.app.Application
import com.nurhidayaatt.core.di.databaseModule
import com.nurhidayaatt.core.di.networkModule
import com.nurhidayaatt.core.di.repositoryModule
import com.nurhidayaatt.moviecatalog.presentation.di.useCaseModule
import com.nurhidayaatt.moviecatalog.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

open class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}