package com.test.asyncnetworking.usecase.github.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Encapsulates networking for Github
 */
object GithubClient {

    val repoApi: RepoApi
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val retrofitClient: Retrofit
    private val gson: Gson = Gson()

    private const val githubApiBaseUrl = "https://api.github.com/"

    init {
        retrofitClient = Retrofit.Builder()
                .baseUrl(githubApiBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        repoApi = retrofitClient.create(RepoApi::class.java)
    }

}
