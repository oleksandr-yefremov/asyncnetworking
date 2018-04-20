package com.test.scalablecapitaltest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.scalablecapitaltest.R
import com.test.scalablecapitaltest.usecase.github.view.RepoListFragment

class GithubReposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, RepoListFragment()).commit()
        }
    }
}