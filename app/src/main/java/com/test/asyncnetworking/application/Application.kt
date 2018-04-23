package com.test.asyncnetworking.application

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.test.asyncnetworking.usecase.github.api.GithubClient
import com.test.asyncnetworking.usecase.github.db.AppDatabase
import com.test.asyncnetworking.usecase.github.db.RepoDao
import com.test.asyncnetworking.usecase.github.repository.RepoRepository
import com.test.asyncnetworking.usecase.github.viewmodel.RepoListViewModel
import com.test.asyncnetworking.usecase.github.viewmodel.RepoWithCommitListViewModel

class Application : Application() {

    // Simplest form of manual DI.
    // Note: this field may not be static, because then Context will leak.
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()

        serviceLocator = ServiceLocator(applicationContext)
    }

    class ServiceLocator(private val applicationContext: Context) {

        private val appDatabase: AppDatabase by lazy {
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "mainDb").build()
        }
        private val repoDao: RepoDao by lazy {
            appDatabase.repoDao()
        }
        private val repoRepository: RepoRepository by lazy {
            RepoRepository(GithubClient.repoApi, repoDao)
        }
        val repoListViewModel: RepoListViewModel by lazy {
            RepoListViewModel(repoRepository)
        }
        val repoWithCommitListViewModel: RepoWithCommitListViewModel by lazy {
            RepoWithCommitListViewModel(repoRepository, repoListViewModel)
        }
    }

}