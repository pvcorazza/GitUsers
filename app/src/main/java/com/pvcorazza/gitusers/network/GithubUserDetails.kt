package com.pvcorazza.gitusers.network

import com.squareup.moshi.Json

// Model class for Github User Details

data class GithubUserDetails(
    val login: String,
    val id: Int,

    @Json(name = "node_id")
    val nodeID: String,

    @Json(name = "avatar_url")
    val avatarURL: String,

    @Json(name = "gravatar_id")
    val gravatarID: String,

    val url: String,

    @Json(name = "html_url")
    val htmlURL: String,

    @Json(name = "followers_url")
    val followersURL: String,

    @Json(name = "following_url")
    val followingURL: String,

    @Json(name = "gists_url")
    val gistsURL: String,

    @Json(name = "starred_url")
    val starredURL: String,

    @Json(name = "subscriptions_url")
    val subscriptionsURL: String,

    @Json(name = "organizations_url")
    val organizationsURL: String,

    @Json(name = "repos_url")
    val reposURL: String,

    @Json(name = "events_url")
    val eventsURL: String,

    @Json(name = "received_events_url")
    val receivedEventsURL: String,

    val type: String,

    @Json(name = "site_admin")
    val siteAdmin: Boolean,

    val name: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Any? = null,
    val bio: Any? = null,

    @Json(name = "public_repos")
    val publicRepos: Int,

    @Json(name = "public_gists")
    val publicGists: Int,

    val followers: Int,
    val following: Int,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "updated_at")
    val updatedAt: String
)
