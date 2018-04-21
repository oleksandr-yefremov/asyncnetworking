package com.test.scalablecapitaltest.di

import com.test.scalablecapitaltest.usecase.github.api.GithubClient
import com.test.scalablecapitaltest.usecase.github.repository.RepoRepository
import com.test.scalablecapitaltest.usecase.github.viewmodel.RepoListViewModel
import com.test.scalablecapitaltest.usecase.github.viewmodel.RepoWithCommitListViewModel

class ServiceLocator {
    companion object {
        private val repoRepository : RepoRepository by lazy {
            RepoRepository(GithubClient.repoApi)
        }
        val repoListViewModel : RepoListViewModel by lazy {
            RepoListViewModel(repoRepository)
        }
        val repoWithCommitListViewModel : RepoWithCommitListViewModel by lazy {
            RepoWithCommitListViewModel(repoRepository)
        }
    }
}