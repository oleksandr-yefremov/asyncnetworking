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
import com.test.asyncnetworking.usecase.github.data.RepoWithCommit
import com.test.asyncnetworking.usecase.github.viewmodel.RepoCommitListener
import com.test.asyncnetworking.usecase.github.viewmodel.RepoWithCommitListViewModel

class RepoWithCommitListFragment: Fragment(), RepoCommitListener {
    private lateinit var repoWithCommitListViewModel: RepoWithCommitListViewModel
    private lateinit var repoListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

        repoWithCommitListViewModel = (activity?.application as Application).serviceLocator.repoWithCommitListViewModel

        repoWithCommitListViewModel.getRepoList(object : Result<List<RepoWithCommit>> {
            override fun onSuccess(data: List<RepoWithCommit>) {
                repoListView.adapter = RepoWithCommitListAdapter(context!!, data as ArrayList<RepoWithCommit>)
            }

            override fun onFailure(error: Throwable) {

            }

        }, this)
    }

    override fun onCommitLoaded(index: Int) {
        Log.d(TAG, "onCommitLoaded : $index")
        (repoListView.adapter as RepoWithCommitListAdapter).notifyDataSetChanged()
    }

    companion object {
        const val TAG = "RepoWithCommitList"
    }
}