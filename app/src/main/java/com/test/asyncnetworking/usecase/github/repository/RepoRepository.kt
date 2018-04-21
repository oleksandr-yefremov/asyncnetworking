package com.test.asyncnetworking.usecase.github.repository

import android.util.Log
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.api.RepoApi
import com.test.asyncnetworking.usecase.github.db.RepoDao
import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class RepoRepository(private val repoApi: RepoApi, private val repoDao: RepoDao) {

    fun getRepos(result: Result<List<Repo>>) {
        return repoApi.getRepos("token  2a16ab5720fea3c78c2ab5eb0545bb6d27c9fd4b","mralexgray", emptyMap())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (!response.isSuccessful) {
                            result.onFailure(HttpException(response))
                            return
                        }

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
        return repoApi.getRepoLastCommit("token  2a16ab5720fea3c78c2ab5eb0545bb6d27c9fd4b", "mralexgray", repoId, emptyMap())
                .enqueue(object : Callback<List<Commit>> {
                    override fun onResponse(call: Call<List<Commit>>, response: Response<List<Commit>>) {
                        if (!response.isSuccessful) {
                            result.onFailure(HttpException(response))
                            return
                        }

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

    private fun getReposFromDatabase(): List<Repo> {
        return repoDao.getAll()
    }

    private fun storeReposInDatabase(repos: List<Repo>) {
        repoDao.insertAll(repos)
    }
}