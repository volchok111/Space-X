package com.volchok.space_x.library.api.di

import com.volchok.space_x.library.api.data.RocketApi
import com.volchok.space_x.library.api.data.RocketRepository
import com.volchok.space_x.library.api.domain.ObserveRocketsUseCase
import com.volchok.space_x.library.api.domain.RemoteRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    factory {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .followSslRedirects(false)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .build()
            .create(RocketApi::class.java)
    }
    factoryOf(::ObserveRocketsUseCase)

    factoryOf(::RocketRepository) bind RemoteRepository::class
}