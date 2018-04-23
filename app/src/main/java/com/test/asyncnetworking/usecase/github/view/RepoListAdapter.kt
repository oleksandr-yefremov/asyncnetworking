package com.test.asyncnetworking.usecase.github.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.test.asyncnetworking.R
import com.test.asyncnetworking.usecase.github.model.Repo

/**
 * Adapts list of repositories data to ListView presentation
 */
class RepoListAdapter(context: Context) : ArrayAdapter<Repo>(context, 0) {

    // TODO: implement ViewHolder
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Prepare convertView
        val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false)

        // Get the data item for this position
        val repo = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        // Lookup view for data population
        val repoNameText = convertView.findViewById<TextView>(R.id.repoNameText)
        val numLikesText = convertView.findViewById<TextView>(R.id.additionalInfoText)

        // Populate the data into the template view using the data object
        repoNameText.text = repo.name
        // TODO: implement string placeholder
        numLikesText.text = "followers: " + repo.stargazersCount.toString()

        // Return the completed view to render on screen
        return convertView
    }
}