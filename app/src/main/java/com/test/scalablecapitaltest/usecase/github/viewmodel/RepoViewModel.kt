package com.test.scalablecapitaltest.usecase.github.viewmodel

import android.util.Log
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.usecase.github.model.Commit
import com.test.scalablecapitaltest.usecase.github.model.Repo
import com.test.scalablecapitaltest.usecase.github.repository.RepoRepository

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
                Log.d(TAG, "getRepoCommits: $lastCommit")
                result.onSuccess(lastCommit)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
