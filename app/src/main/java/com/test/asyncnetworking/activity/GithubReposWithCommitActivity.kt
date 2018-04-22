package com.test.asyncnetworking.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.asyncnetworking.R
import com.test.asyncnetworking.usecase.github.view.RepoWithCommitListFragment

class GithubReposWithCommitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_github_repos)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, RepoWithCommitListFragment()).commit()
        }
    }
}