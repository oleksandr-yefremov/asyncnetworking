package com.test.scalablecapitaltest.usecase.github.viewmodel

import android.util.Log
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.usecase.github.model.Repo
import com.test.scalablecapitaltest.usecase.github.repository.RepoRepository

class RepoListViewModel(private val repoRepository: RepoRepository) {

    fun getRepoList(result: Result<List<Repo>>) {
        return repoRepository.getRepos(object : Result<List<Repo>> {
            override fun onFailure(error: Throwable) {
                result.onFailure(error)
            }

            override fun onSuccess(data: List<Repo>) {
                Log.d(TAG, "getRepoList : ${data.size}")
                result.onSuccess(data)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
