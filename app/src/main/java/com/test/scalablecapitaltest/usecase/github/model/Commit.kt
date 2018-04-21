package com.test.scalablecapitaltest.usecase.github.model

import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
data class Commit(

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("commit")
	val commit: Commit? = null,

	@field:SerializedName("comments_url")
	val commentsUrl: String? = null,

	@field:SerializedName("sha")
	val sha: String? = null,

	@field:SerializedName("url")
	val url: String? = null

)