package com.test.asyncnetworking.usecase.github.model

/**
 * Data holder for a single repo with optional commit field
 */
data class RepoWithCommit(
        val repo: Repo,
        var commit: Commit? = null
)