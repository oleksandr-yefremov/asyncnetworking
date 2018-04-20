package com.test.scalablecapitaltest.usecase.github.repository

import android.util.Log
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.usecase.github.api.RepoApi
import com.test.scalablecapitaltest.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository(private val repoApi: RepoApi) {

    fun getRepos(result: Result<List<Repo>>) {
        return repoApi.getRepos("mralexgray", emptyMap())
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
}