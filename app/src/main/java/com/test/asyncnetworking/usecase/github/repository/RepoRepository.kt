package com.test.asyncnetworking.usecase.github.repository

import android.util.Log
import com.test.asyncnetworking.common.CachedResult
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.api.RepoApi
import com.test.asyncnetworking.usecase.github.db.RepoDao
import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import ru.gildor.coroutines.retrofit.awaitResponse

/**
 * Business logic for data manipulation. Encapsulates handling DB, API requests, caching etc.
 */
class RepoRepository(private val repoApi: RepoApi, private val repoDao: RepoDao) {

    suspend fun getRepos(result: CachedResult<List<Repo>>) {
        val cachedData = getReposFromDatabase().await()
        Log.d(TAG, "async getReposFromDatabase() finished")
        result.onSuccess(cachedData, isCache = true)

        Log.d(TAG, Thread.currentThread().name)

        try {
            // put "token <your-oauth-token>" instead of empty string to bypass API rate limits
            val response = repoApi.getRepos("", "mralexgray", emptyMap()).awaitResponse()
            if (!response.isSuccessful) {
                result.onFailure(HttpException(response))
                return
            }

            Log.d(TAG, "repoApi.getRepos.onResponse")
            val data = response.body()

            if (data != null) {
                storeReposInDatabase(data)
                result.onSuccess(data, isCache = false)
            } else {
                result.onSuccess(emptyList(), isCache = false)
            }
        } catch (error: Throwable) {
            Log.d(TAG, "repoApi.getRepos.onResponse $error")
            result.onFailure(error)
        }
    }

    fun getRepoCommits(repoId: String, result: Result<List<Commit>>) {
        // put "token <your-oauth-token>" instead of empty string to bypass API rate limits
        repoApi.getRepoLastCommit("", "mralexgray", repoId, emptyMap())
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
                        Log.e(TAG, "API request failed")
                        result.onFailure(t)
                    }
                })
    }

    private fun getReposFromDatabase(): Deferred<List<Repo>> {
        return async(CommonPool) {
            val repos = repoDao.getAll()
            Log.d(TAG, "getReposFromDatabase : ${repos.size} items")
            repos
        }
    }

    private fun storeReposInDatabase(repos: List<Repo>) {
        async(CommonPool) {
            repoDao.insertAll(repos)
            Log.d(TAG, "storeReposInDatabase : ${repos.size} items")
        }
    }

    companion object {
        const val TAG = "RepoRepository"
    }
}