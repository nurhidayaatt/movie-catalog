package com.nurhidayaatt.core.di

import androidx.room.Room
import com.nurhidayaatt.core.data.source.MainRepository
import com.nurhidayaatt.core.data.source.local.LocalDataSource
import com.nurhidayaatt.core.data.source.local.room.MainDatabase
import com.nurhidayaatt.core.data.source.remote.RemoteDataSource
import com.nurhidayaatt.core.data.source.remote.network.ApiService
import com.nurhidayaatt.core.domain.repository.IMainRepository
import com.nurhidayaatt.core.utils.Constants.BASE_URL
import com.nurhidayaatt.core.utils.Constants.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MainDatabase>().mainDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMainRepository> {
        MainRepository(get(), get())
    }
}