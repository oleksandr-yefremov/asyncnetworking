package com.test.scalablecapitaltest.usecase.github.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.test.scalablecapitaltest.R
import com.test.scalablecapitaltest.common.Result
import com.test.scalablecapitaltest.di.ServiceLocator
import com.test.scalablecapitaltest.usecase.github.model.Repo

class RepoListFragment: Fragment() {

    private val repoListViewModel = ServiceLocator.repoListViewModel
    private lateinit var repoListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

        repoListViewModel.getRepoList(object : Result<List<Repo>> {
            override fun onSuccess(data: List<Repo>) {
                repoListView.adapter = RepoListAdapter(context!!, data as ArrayList<Repo>)
            }

            override fun onFailure(error: Throwable) {

            }

        })
    }
}