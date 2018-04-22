package com.test.asyncnetworking.usecase.github.viewmodel

import android.util.Log
import com.test.asyncnetworking.common.CachedResult
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.repository.RepoRepository

class RepoListViewModel(private val repoRepository: RepoRepository) {

    suspend fun getRepoList(result: CachedResult<List<Repo>>) {
        return repoRepository.getRepos(object : CachedResult<List<Repo>> {
            override fun onFailure(error: Throwable) {
                result.onFailure(error)
            }

            override fun onSuccess(data: List<Repo>, isCache: Boolean) {
                Log.d(TAG, "got repoList : ${data.size}")
                result.onSuccess(data, isCache)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
