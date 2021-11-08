package com.pvcorazza.gitusers.repository

import com.pvcorazza.gitusers.network.GithubApi
import com.pvcorazza.gitusers.utils.safeApiCall

class GithubRepository {
    private val client = GithubApi.retrofitService

    suspend fun getGithubProperties() = safeApiCall { client.getGithubProperties() }
    suspend fun getGithubUserDetails(id: Int) = safeApiCall { client.getGithubUserDetails(id) }
}