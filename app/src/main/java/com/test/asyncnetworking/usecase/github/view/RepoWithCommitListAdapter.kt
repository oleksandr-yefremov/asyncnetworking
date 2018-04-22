package com.test.asyncnetworking.usecase.github.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.test.asyncnetworking.R
import com.test.asyncnetworking.usecase.github.data.RepoWithCommit

class RepoWithCommitListAdapter(context: Context, repos: ArrayList<RepoWithCommit>) : ArrayAdapter<RepoWithCommit>(context, 0, repos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Prepare convertView
        val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false)

        // Get the data item for this position
        val repo = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        // Lookup view for data population
        val repoNameText = convertView.findViewById<TextView>(R.id.repoNameText)
        val commitShaText = convertView.findViewById<TextView>(R.id.commitShaText)

        // Populate the data into the template view using the data object
        repoNameText.text = repo.repo.name
        val commit = repo.commit
        if (commit != null) {
            commitShaText.text = commit.sha
        } else {
            commitShaText.text = ""
        }


        // Return the completed view to render on screen
        return convertView
    }
}