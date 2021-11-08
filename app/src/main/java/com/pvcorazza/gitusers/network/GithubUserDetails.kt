package com.pvcorazza.gitusers.network

import com.google.gson.annotations.SerializedName

// Model class for Github User Details

data class GithubUserDetails(
    val login: String?,
    val id: Int?,

    @SerializedName("node_id")
    val nodeID: String?,

    @SerializedName("avatar_url")
    val avatarURL: String?,

    @SerializedName("gravatar_id")
    val gravatarID: String?,

    val url: String?,

    @SerializedName("html_url")
    val htmlURL: String?,

    @SerializedName("followers_url")
    val followersURL: String?,

    @SerializedName("following_url")
    val followingURL: String?,

    @SerializedName("gists_url")
    val gistsURL: String?,

    @SerializedName("starred_url")
    val starredURL: String?,

    @SerializedName("subscriptions_url")
    val subscriptionsURL: String?,

    @SerializedName("organizations_url")
    val organizationsURL: String?,

    @SerializedName("repos_url")
    val reposURL: String?,

    @SerializedName("events_url")
    val eventsURL: String?,

    @SerializedName("received_events_url")
    val receivedEventsURL: String?,

    val type: String?,

    @SerializedName("site_admin")
    val siteAdmin: Boolean?,

    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Any? = null,
    val bio: Any? = null,

    @SerializedName("public_repos")
    val publicRepos: Int?,

    @SerializedName("public_gists")
    val publicGists: Int?,

    val followers: Int?,
    val following: Int?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
)
