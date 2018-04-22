package com.test.asyncnetworking.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.test.asyncnetworking.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gotoRepoListActivityButton)
                .setOnClickListener({
                    startActivity(Intent(this, GithubReposActivity::class.java))
                })

        findViewById<Button>(R.id.gotoRepoWithCommitListActivityButton)
                .setOnClickListener({
                    startActivity(Intent(this, GithubReposWithCommitActivity::class.java))
                })
    }
}