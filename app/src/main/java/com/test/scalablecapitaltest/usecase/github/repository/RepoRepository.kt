package com.test.scalablecapitaltest.usecase.github.repository

import android.util.Log
import com.test.scalablecapitaltest.usecase.github.api.RepoApi
import com.test.scalablecapitaltest.usecase.github.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository(private val repoApi: RepoApi) {

    fun getRepos(callback: Callback<List<Repo>>) {
        return repoApi.getRepos("oleksandr-yefremov", emptyMap())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        callback.onResponse(call, response)
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Log.e(this.javaClass.simpleName, "API request failed")
                        callback.onFailure(call, t)
                    }
                })
    }
}