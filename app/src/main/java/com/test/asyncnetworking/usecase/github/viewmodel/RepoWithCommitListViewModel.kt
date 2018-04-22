package com.test.asyncnetworking.usecase.github.viewmodel

import android.util.Log
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.data.RepoWithCommit
import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.repository.RepoRepository

class RepoWithCommitListViewModel(private val repoRepository: RepoRepository, private val repoListViewModel: RepoListViewModel) {

    lateinit var repoViewModelList: ArrayList<RepoViewModel>

    suspend fun getRepoList(result: Result<List<RepoWithCommit>>, repoCommitListener: RepoCommitListener) {
        return repoListViewModel.getRepoList(object : Result<List<Repo>> {
            override fun onFailure(error: Throwable) {
                result.onFailure(error)
            }

            override fun onSuccess(data: List<Repo>) {
                Log.d(RepoListViewModel.TAG, "getRepoList: $data")

                val repoWithCommitList = ArrayList<RepoWithCommit>(data.size)
                repoViewModelList = ArrayList(data.size)

                data.forEachIndexed { index, repo ->
                    val repoViewModel = RepoViewModel(repoRepository, repo)
                    repoViewModelList.add(repoViewModel)

                    val repoWithCommit = RepoWithCommit(repo)
                    repoWithCommitList.add(repoWithCommit)

                    repoViewModel.getLastCommit(object : Result<Commit?> {

                        override fun onSuccess(data: Commit?) {
                            repoWithCommit.commit = data
                            repoCommitListener.onCommitLoaded(index)
                        }

                        override fun onFailure(error: Throwable) {
                            Log.d(TAG, "repoViewModel.getLastCommit failed", error)
                        }
                    })
                }

                result.onSuccess(repoWithCommitList)
            }
        })
    }

    companion object {
        const val TAG = "RepoListViewModel"
    }
}
