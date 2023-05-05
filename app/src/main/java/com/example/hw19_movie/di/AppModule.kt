package com.example.hw19_movie.di

import android.app.Application
import androidx.room.Room
import com.example.hw19_movie.data.local.db.IMovieDao
import com.example.hw19_movie.data.local.db.MovieDataBase
import com.example.hw19_movie.data.network.IMovieNetwork
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.util.API_KEY
import com.example.hw19_movie.util.BASE_URL
import com.example.hw19_movie.util.LANGUAGE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", LANGUAGE)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkkHttpLog(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
//        .connectTimeout(3, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): IMovieNetwork = retrofit.create(
        IMovieNetwork::class.java
    )

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MovieDataBase {
        return Room.databaseBuilder(application, MovieDataBase::class.java, "movie_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDataBase: MovieDataBase): IMovieDao {
        return movieDataBase.movieDao()
    }

    @Provides
    @Singleton
    fun provideRepository(iMovieNetwork: IMovieNetwork, iMovieDao: IMovieDao): Repository {
        return Repository(iMovieNetwork, iMovieDao)
    }

}