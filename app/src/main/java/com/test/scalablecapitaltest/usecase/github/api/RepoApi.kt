package com.test.scalablecapitaltest.usecase.github.api

import com.test.scalablecapitaltest.usecase.github.model.Commit
import com.test.scalablecapitaltest.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RepoApi {

    // ?per_page=100
    @GET("users/{user_id}/repos?per_page=100")
    fun getRepos(@Header("Authorization") authorization: String, @Path("user_id") userId: String, @QueryMap requestMap: Map<String, String>): Call<List<Repo>>

    @GET("repos/{user_id}/{repo_id}/commits?per_page=1")
    fun getRepoLastCommit(@Header("Authorization") authorization: String, @Path("user_id") userId: String, @Path("repo_id") repoId: String, @QueryMap requestMap: Map<String, String>): Call<List<Commit>>

}