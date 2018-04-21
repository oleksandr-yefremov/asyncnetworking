package com.test.asyncnetworking.usecase.github.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.test.asyncnetworking.usecase.github.model.Repo

@Dao
interface RepoDao {

    @Query("SELECT * FROM repos")
    fun getAll(): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: Repo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<Repo>)
}