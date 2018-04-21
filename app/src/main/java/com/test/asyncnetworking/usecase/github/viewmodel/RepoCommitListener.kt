package com.test.asyncnetworking.usecase.github.viewmodel

/**
 * Given the list of repos and commit in each repo,
 * notifies when commit for repo at given index is loaded
 */
interface RepoCommitListener {

    fun onCommitLoaded(index: Int)

}