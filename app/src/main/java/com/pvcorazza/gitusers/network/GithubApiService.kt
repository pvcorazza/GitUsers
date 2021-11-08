package com.pvcorazza.gitusers.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Get data with Retrofit using Moshi converter

private const val BASE_URL = "https://api.github.com/"

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the API methods
 */
interface GithubApiService {
    @GET("users")
    suspend fun getGithubProperties():
            Response<List<GithubProperty>>

    @GET("/user/{id}")
    suspend fun getGithubUserDetails(@Path("id") id: Int):
            Response<GithubUserDetails>
}

object GithubApi {
    val retrofitService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}