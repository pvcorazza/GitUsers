package com.pvcorazza.gitusers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pvcorazza.gitusers.network.GithubProperty
import com.pvcorazza.gitusers.repository.GithubRepository
import com.pvcorazza.gitusers.utils.ResponseEmpty
import com.pvcorazza.gitusers.utils.ResponseError
import com.pvcorazza.gitusers.utils.ResponseSuccess
import com.pvcorazza.gitusers.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ViewModel class with livedata
class HomeViewModel : ViewModel() {

    private val repository: GithubRepository = GithubRepository()

    private val _githubProperties: MutableLiveData<List<GithubProperty>?> = MutableLiveData()
    val githubProperties: LiveData<List<GithubProperty>?> = _githubProperties

    private val _loadingGithubProperties: MutableLiveData<Status> = MutableLiveData()
    val loadingGithubProperties: LiveData<Status> = _loadingGithubProperties

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg


    val loadGithubProperties = viewModelScope.launch {

        _loadingGithubProperties.value = Status.LOADING

        val response = withContext(Dispatchers.IO) {
            repository.getGithubProperties()
        }

        when (response) {
            is ResponseSuccess -> {
                _githubProperties.value = response.body
                _loadingGithubProperties.value = Status.SUCCESS
            }
            is ResponseError, is ResponseEmpty -> {
                _loadingGithubProperties.value = Status.ERROR
            }
        }

    }
}
