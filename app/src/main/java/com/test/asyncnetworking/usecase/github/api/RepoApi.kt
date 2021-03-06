package com.test.asyncnetworking.usecase.github.api

import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Retrofit API calls for Github
 */
interface RepoApi {

    @GET("users/{user_id}/repos?per_page=30")
    fun getRepos(@Header("Authorization") authorization: String,
                 @Path("user_id") userId: String,
                 @QueryMap requestMap: Map<String, String>): Call<List<Repo>>

    @GET("repos/{user_id}/{repo_id}/commits?per_page=1")
    fun getRepoLastCommit(@Header("Authorization") authorization: String,
                          @Path("user_id") userId: String,
                          @Path("repo_id") repoId: String,
                          @QueryMap requestMap: Map<String, String>): Call<List<Commit>>

}