package com.test.asyncnetworking.usecase.github.viewmodel

import android.util.Log
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.repository.RepoRepository

class RepoListViewModel(private val repoRepository: RepoRepository) {

    suspend fun getRepoList(result: Result<List<Repo>>) {
        return repoRepository.getRepos(object : Result<List<Repo>> {
            override fun onFailure(error: Throwable) {
                result.onFailure(error)
            }

            override fun onSuccess(data: List<Repo>) {
                Log.d(TAG, "got repoList : ${data.size}")
                result.onSuccess(data)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
