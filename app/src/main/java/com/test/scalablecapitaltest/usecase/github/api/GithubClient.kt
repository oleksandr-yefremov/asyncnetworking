package com.test.scalablecapitaltest.usecase.github.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val retrofitClient: Retrofit
    val REPO_API: RepoApi
    private val gson: Gson = Gson()

    private val githubApiBaseUrl = "https://api.github.com/"

    init {

        retrofitClient = Retrofit.Builder()
                .baseUrl(githubApiBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        REPO_API = retrofitClient.create(RepoApi::class.java)
    }

}
