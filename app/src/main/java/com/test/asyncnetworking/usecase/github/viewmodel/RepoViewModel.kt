package com.test.asyncnetworking.usecase.github.viewmodel

import android.util.Log
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.repository.RepoRepository

/**
 * Mediates communication between View and Repository for a single repo use case.
 */
class RepoViewModel(private val repoRepository: RepoRepository, val repo: Repo) {

    fun getLastCommit(result: Result<Commit?>) {
        return repoRepository.getRepoCommits(repo.name, object : Result<List<Commit>> {
            override fun onFailure(error: Throwable) {
                result.onFailure(error)
            }

            override fun onSuccess(data: List<Commit>) {
                if (data.isEmpty()) {
                    result.onSuccess(null)
                    return
                }

                val lastCommit = data.first()
                Log.d(TAG, "getRepoLastCommit: $lastCommit")
                result.onSuccess(lastCommit)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
