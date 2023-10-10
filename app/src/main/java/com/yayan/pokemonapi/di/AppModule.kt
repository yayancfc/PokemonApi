package com.yayan.pokemonapi.di

import android.content.Context
import com.yayan.pokemonapi.data.local.PokemonDAO
import com.yayan.pokemonapi.data.local.PokemonDatabase
import com.yayan.pokemonapi.data.local.PokemonLocalRepository
import com.yayan.pokemonapi.data.remote.ApiServices
import com.yayan.pokemonapi.data.remote.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providePokeApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PokemonDatabase {
        return PokemonDatabase.getInstance(context)
    }

    @Provides
    fun providePokemonDAO(pokemonDatabase: PokemonDatabase) : PokemonDAO {
        return pokemonDatabase.getPokemonDAO()
    }

    @Provides
    @Singleton
    fun provideRepository(movieRepository: ApiServices): PokemonRepository {
        return PokemonRepository(movieRepository)
    }


    @Provides
    @Singleton
    fun provideGetLocalUseCase(dao: PokemonDAO): PokemonLocalRepository {
        return PokemonLocalRepository(dao)
    }
}
