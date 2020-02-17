package com.pvcorazza.gitusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.pvcorazza.gitusers.repository.GithubRepository
import com.pvcorazza.gitusers.utils.Resource
import kotlinx.coroutines.Dispatchers

// ViewModel class with livedata
class DetailsViewModel(id: Int) : ViewModel() {

    val repository: GithubRepository = GithubRepository()

    val githubUserDetails = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = repository.getGithubUserDetails(id)
        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message()))
        }
    }
}

class DetailsViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(id) as T
    }
}
