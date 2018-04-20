package com.test.scalablecapitaltest.di

import com.test.scalablecapitaltest.usecase.github.api.GithubClient
import com.test.scalablecapitaltest.usecase.github.repository.RepoRepository
import com.test.scalablecapitaltest.usecase.github.viewmodel.RepoListViewModel

class ServiceLocator {
    companion object {
        private val repoRepository : RepoRepository by lazy {
            RepoRepository(GithubClient.REPO_API)
        }
        val repoListViewModel : RepoListViewModel by lazy {
            RepoListViewModel(repoRepository)
        }
    }
}