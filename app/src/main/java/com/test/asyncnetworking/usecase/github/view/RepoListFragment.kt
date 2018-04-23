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
import com.test.asyncnetworking.common.CachedResult
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.viewmodel.RepoListViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Displays ListView fetching UI data from ViewModel
 */
class RepoListFragment: Fragment() {

    private lateinit var repoListViewModel: RepoListViewModel
    private lateinit var repoListView: ListView
    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

        repoListViewModel = (activity?.application as Application).serviceLocator.repoListViewModel
        repoListAdapter = RepoListAdapter(context!!)
        repoListAdapter.setNotifyOnChange(true)
        repoListView.adapter = repoListAdapter

        // launch coroutine which runs suspending functions
        launch(UI) {
            repoListViewModel.getRepoList(object : CachedResult<List<Repo>> {
                override fun onSuccess(data: List<Repo>, isCache: Boolean) {
                    repoListAdapter.clear()
                    repoListAdapter.addAll(data as ArrayList<Repo>)
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "error : ", error)
                }

            })
        }
    }

    companion object {
        const val TAG = "RepoListFragment"
    }
}