package com.test.scalablecapitaltest.usecase.github.api

import com.test.scalablecapitaltest.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RepoApi {

    @GET("users/{user_id}/repos")
    fun getRepos(@Path("user_id") userId: String, @QueryMap requestMap: Map<String, String>): Call<List<Repo>>

}