package com.test.scalablecapitaltest.usecase.github.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.test.scalablecapitaltest.R
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.di.ServiceLocator
import com.test.scalablecapitaltest.usecase.github.data.RepoWithCommit
import com.test.scalablecapitaltest.usecase.github.viewmodel.RepoCommitListener

class RepoWithCommitListFragment: Fragment(), RepoCommitListener {
    private val repoWithCommitListViewModel = ServiceLocator.repoWithCommitListViewModel
    private lateinit var repoListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

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