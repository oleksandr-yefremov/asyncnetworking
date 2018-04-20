package com.test.scalablecapitaltest.usecase.github

import com.google.gson.Gson
import com.test.scalablecapitaltest.usecase.github.api.ReposApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubClient() {

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val retrofitClient: Retrofit
    private val reposApi: ReposApi
    private val gson: Gson = Gson()

    private val githubApiBaseUrl = "https://api.github.com/"

    init {

        retrofitClient = Retrofit.Builder()
                .baseUrl(githubApiBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        reposApi = retrofitClient.create(ReposApi::class.java)
    }

}
