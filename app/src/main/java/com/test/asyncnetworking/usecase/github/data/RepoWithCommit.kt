package com.test.asyncnetworking.usecase.github.data

import com.test.asyncnetworking.usecase.github.model.Commit
import com.test.asyncnetworking.usecase.github.model.Repo

data class RepoWithCommit(
        val repo: Repo,
        var commit: Commit? = null
)