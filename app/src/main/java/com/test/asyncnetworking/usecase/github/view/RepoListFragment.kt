package com.test.asyncnetworking.usecase.github.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.test.asyncnetworking.R
import com.test.asyncnetworking.application.Application
import com.test.asyncnetworking.common.Result
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.viewmodel.RepoListViewModel

class RepoListFragment: Fragment() {

    private lateinit var repoListViewModel: RepoListViewModel
    private lateinit var repoListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoListView = inflate.findViewById(R.id.repoListView)
        return inflate
    }

    override fun onStart() {
        super.onStart()

        repoListViewModel = (activity?.application as Application).serviceLocator.repoListViewModel
        repoListViewModel.getRepoList(object : Result<List<Repo>> {
            override fun onSuccess(data: List<Repo>) {
                repoListView.adapter = RepoListAdapter(context!!, data as ArrayList<Repo>)
            }

            override fun onFailure(error: Throwable) {

            }

        })
    }
}