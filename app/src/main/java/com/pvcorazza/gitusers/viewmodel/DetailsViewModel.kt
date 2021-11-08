package com.pvcorazza.gitusers.viewmodel

import androidx.lifecycle.*
import com.pvcorazza.gitusers.network.GithubUserDetails
import com.pvcorazza.gitusers.repository.GithubRepository
import com.pvcorazza.gitusers.utils.ResponseEmpty
import com.pvcorazza.gitusers.utils.ResponseError
import com.pvcorazza.gitusers.utils.ResponseSuccess
import com.pvcorazza.gitusers.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ViewModel class with livedata
class DetailsViewModel(id: Int) : ViewModel() {

    private val repository: GithubRepository = GithubRepository()

    private val _githubUserDetails: MutableLiveData<GithubUserDetails?> = MutableLiveData()
    val githubUserDetails: LiveData<GithubUserDetails?> = _githubUserDetails

    private val _loadingGithubUserDetails: MutableLiveData<Status> = MutableLiveData()
    val loadingGithubUserDetails: LiveData<Status> = _loadingGithubUserDetails

    val loadGithubUserDetails = viewModelScope.launch {

        _loadingGithubUserDetails.value = Status.LOADING

        val response = withContext(Dispatchers.IO) {
            repository.getGithubUserDetails(id)
        }

        when (response) {
            is ResponseSuccess -> {
                _githubUserDetails.value = response.body
                _loadingGithubUserDetails.value = Status.SUCCESS
            }
            is ResponseError, is ResponseEmpty -> _loadingGithubUserDetails.value = Status.ERROR
        }

    }
}

class DetailsViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(id) as T
    }
}
