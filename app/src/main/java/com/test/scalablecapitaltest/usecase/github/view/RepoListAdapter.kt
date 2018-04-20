package com.test.scalablecapitaltest.usecase.github.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.test.scalablecapitaltest.R
import com.test.scalablecapitaltest.usecase.github.model.Repo

class RepoListAdapter(context: Context, users: ArrayList<Repo>) : ArrayAdapter<Repo>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Prepare convertView
        val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false)

        // Get the data item for this position
        val repo = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        // Lookup view for data population
        val repoNameText = convertView.findViewById<TextView>(R.id.repoNameText)
        val numLikesText = convertView.findViewById<TextView>(R.id.numLikesText)
        val numForksText = convertView.findViewById<TextView>(R.id.numForksText)

        // Populate the data into the template view using the data object
        repoNameText.text = repo.name
        numLikesText.text = repo.stargazersCount.toString()
        numForksText.text = repo.forksCount.toString()

        // Return the completed view to render on screen
        return convertView
    }
}