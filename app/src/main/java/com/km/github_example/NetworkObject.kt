package com.km.github_example

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkObject {
    val networkFlipperPlugin = NetworkFlipperPlugin()

    private val httpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(" https://api.github.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getGithubApi() : GithubApi = retrofit.create(GithubApi::class.java)
}
