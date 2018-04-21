package com.test.scalablecapitaltest.usecase.github.data

import com.test.scalablecapitaltest.usecase.github.model.Commit
import com.test.scalablecapitaltest.usecase.github.model.Repo

data class RepoWithCommit(
        val repo: Repo,
        var commit: Commit? = null
)