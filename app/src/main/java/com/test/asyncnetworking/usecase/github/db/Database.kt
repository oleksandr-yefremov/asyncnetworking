package com.test.asyncnetworking.usecase.github.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.test.asyncnetworking.usecase.github.model.Repo

@Database(entities = arrayOf(Repo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}