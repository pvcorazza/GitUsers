package com.pvcorazza.gitusers.repository

import com.pvcorazza.gitusers.network.GithubApi

class GithubRepository {
    private val client = GithubApi.retrofitService

    suspend fun getGithubProperties() = client.getGithubProperties()
    suspend fun getGithubUserDetails(id: Int) = client.getGithubUserDetails(id)
}