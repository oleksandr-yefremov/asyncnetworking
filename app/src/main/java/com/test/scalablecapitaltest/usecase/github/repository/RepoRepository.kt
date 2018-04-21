package com.test.scalablecapitaltest.usecase.github.repository

import android.util.Log
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.usecase.github.api.RepoApi
import com.test.scalablecapitaltest.usecase.github.model.Commit
import com.test.scalablecapitaltest.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository(private val repoApi: RepoApi) {

    fun getRepos(result: Result<List<Repo>>) {
        return repoApi.getRepos("token  2a16ab5720fea3c78c2ab5eb0545bb6d27c9fd4b","mralexgray", emptyMap())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        val body = response.body()
                        if (body != null) {
                            result.onSuccess(body)
                        } else {
                            result.onSuccess(emptyList())
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Log.e(this.javaClass.simpleName, "API request failed")
                        result.onFailure(t)
                    }
                })
    }

    fun getRepoCommits(repoId: String, result: Result<List<Commit>>) {
        return repoApi.getRepoCommits("token  2a16ab5720fea3c78c2ab5eb0545bb6d27c9fd4b", "mralexgray", repoId, emptyMap())
                .enqueue(object : Callback<List<Commit>> {
                    override fun onResponse(call: Call<List<Commit>>, response: Response<List<Commit>>) {
                        val body = response.body()
                        if (body != null) {
                            result.onSuccess(body)
                        } else {
                            result.onSuccess(emptyList())
                        }
                    }

                    override fun onFailure(call: Call<List<Commit>>, t: Throwable) {
                        Log.e(this.javaClass.simpleName, "API request failed")
                        result.onFailure(t)
                    }
                })
    }
}