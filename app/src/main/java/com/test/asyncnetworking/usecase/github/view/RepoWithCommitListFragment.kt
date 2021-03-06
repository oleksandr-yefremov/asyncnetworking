package com.test.asyncnetworking.usecase.github.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.test.asyncnetworking.R
import com.test.asyncnetworking.application.Application
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.model.RepoWithCommit
import com.test.asyncnetworking.usecase.github.viewmodel.RepoCommitListener
import com.test.asyncnetworking.usecase.github.viewmodel.RepoWithCommitListViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Displays ListView fetching UI data from ViewModel.
 * Gets notified when a commit for every repo is loaded and ListView can be updated.
 */
class RepoWithCommitListFragment: Fragment(), RepoCommitListener {
    private lateinit var repoWithCommitListViewModel: RepoWithCommitListViewModel
    private lateinit var repoListView: ListView
    private var repoList = ArrayList<RepoWithCommit>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

        // init adapter with empty list
        val repoWithCommitListAdapter = RepoWithCommitListAdapter(context!!, repoList)
        repoListView.adapter = repoWithCommitListAdapter

        repoWithCommitListViewModel = (activity?.application as Application).serviceLocator.repoWithCommitListViewModel
        launch(UI) {
            repoWithCommitListViewModel.getRepoList(object : Result<List<RepoWithCommit>> {
                override fun onSuccess(data: List<RepoWithCommit>) {
                    repoList.clear()
                    repoList.addAll(data as ArrayList<RepoWithCommit>)
                    repoWithCommitListAdapter.notifyDataSetChanged()
                }

                override fun onFailure(error: Throwable) {

                }

            }, this@RepoWithCommitListFragment)
        }
    }

    override fun onCommitLoaded(index: Int) {
        Log.d(TAG, "onCommitLoaded : $index")

        // TODO: notify only changed rows, not the whole dataset (e.g. by row id)
        (repoListView.adapter as RepoWithCommitListAdapter).notifyDataSetChanged()
    }

    companion object {
        const val TAG = "RepoWithCommitList"
    }
}