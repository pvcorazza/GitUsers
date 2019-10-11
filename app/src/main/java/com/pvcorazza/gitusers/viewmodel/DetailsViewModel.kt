package com.pvcorazza.gitusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pvcorazza.gitusers.network.GithubApi
import com.pvcorazza.gitusers.network.GithubUserDetails
import com.pvcorazza.gitusers.utils.GithubApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// ViewModel class with livedata
class DetailsViewModel(id: Int) : ViewModel() {

    /* Variables for data binding */
    private val _status = MutableLiveData<GithubApiStatus>()

    val status: LiveData<GithubApiStatus>
        get() = _status

    private val _githubUserDetails = MutableLiveData<GithubUserDetails>()
    val githubUserDetails: LiveData<GithubUserDetails>
        get() = _githubUserDetails

    // Job for coroutine
    private var viewModelJob = Job()
    // New coroutine
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Fetch data on start
    init {
        _status.value = GithubApiStatus.LOADING
        getGithubUserDetailsRealStateProperties(id)
    }

    // Get async data from JSON
    private fun getGithubUserDetailsRealStateProperties(id: Int) {
        coroutineScope.launch {

            val getGithubUserDetailsDeferred = GithubApi.retrofitService.getGithubUserDetails(id)

            try {
                _githubUserDetails.value = getGithubUserDetailsDeferred.await()
                _status.value = GithubApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GithubApiStatus.ERROR
                e.printStackTrace()
            }
        }
    }

    // Cancel job at end
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

class DetailsViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(id) as T
    }
}
