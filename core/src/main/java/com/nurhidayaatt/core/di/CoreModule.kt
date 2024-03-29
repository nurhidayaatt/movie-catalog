package com.nurhidayaatt.core.di

import androidx.room.Room
import com.nurhidayaatt.core.BuildConfig
import com.nurhidayaatt.core.data.source.MainRepository
import com.nurhidayaatt.core.data.source.local.LocalDataSource
import com.nurhidayaatt.core.data.source.local.room.MainDatabase
import com.nurhidayaatt.core.data.source.remote.RemoteDataSource
import com.nurhidayaatt.core.data.source.remote.network.ApiService
import com.nurhidayaatt.core.domain.repository.IMainRepository
import com.nurhidayaatt.core.utils.Constants.BASE_URL
import com.nurhidayaatt.core.utils.Constants.DATABASE_NAME
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("nurhidayaatt".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MainDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .certificatePinner(certificatePinner)
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