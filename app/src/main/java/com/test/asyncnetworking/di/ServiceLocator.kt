package com.test.asyncnetworking.di

import android.arch.persistence.room.Room
import android.content.Context
import com.test.asyncnetworking.usecase.github.api.GithubClient
import com.test.asyncnetworking.usecase.github.db.AppDatabase
import com.test.asyncnetworking.usecase.github.db.RepoDao
import com.test.asyncnetworking.usecase.github.repository.RepoRepository
import com.test.asyncnetworking.usecase.github.viewmodel.RepoListViewModel
import com.test.asyncnetworking.usecase.github.viewmodel.RepoWithCommitListViewModel

class ServiceLocator {
    companion object {
        fun appDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, "mainDb").build()
        }
        private val repoDao: RepoDao by lazy {
            appDatabase().userDao()
        }
        private val repoRepository : RepoRepository by lazy {
            RepoRepository(GithubClient.repoApi, )
        }
        val repoListViewModel : RepoListViewModel by lazy {
            RepoListViewModel(repoRepository)
        }
        val repoWithCommitListViewModel : RepoWithCommitListViewModel by lazy {
            RepoWithCommitListViewModel(repoRepository)
        }
    }
}