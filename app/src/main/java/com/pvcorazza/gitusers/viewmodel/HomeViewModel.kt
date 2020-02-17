package com.pvcorazza.gitusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pvcorazza.gitusers.network.GithubProperty
import com.pvcorazza.gitusers.repository.GithubRepository
import com.pvcorazza.gitusers.utils.Resource
import kotlinx.coroutines.Dispatchers

// ViewModel class with livedata
class HomeViewModel : ViewModel() {

    val repository: GithubRepository = GithubRepository()

    private lateinit var githubListResult: List<GithubProperty>

    val githubProperties = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = repository.getGithubProperties()
        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message()))
        }
    }
}
