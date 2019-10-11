package com.pvcorazza.gitusers.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Get data with Retrofit using Moshi converter

private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface GithubApiService {
    @GET("users")
    fun getGithubProperties():
            Deferred<List<GithubProperty>>

    @GET("/user/{id}")
    fun getGithubUserDetails(@Path("id") id: Int):
            Deferred<GithubUserDetails>
}

object GithubApi {

    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val retrofit = Retrofit.Builder().client(provideOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()


val retrofitService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}