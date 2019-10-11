package com.pvcorazza.gitusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pvcorazza.gitusers.utils.GithubApiStatus
import com.pvcorazza.gitusers.network.GithubApi
import com.pvcorazza.gitusers.network.GithubProperty
import kotlinx.coroutines.*

// ViewModel class with livedata
class HomeViewModel : ViewModel() {

    /* Variables for data binding */
    private val _status = MutableLiveData<GithubApiStatus>()
    val status: LiveData<GithubApiStatus>
        get() = _status

    private lateinit var githubListResult: List<GithubProperty>

    private val _githubProperties = MutableLiveData<List<GithubProperty>>()
    val githubProperties: LiveData<List<GithubProperty>>
        get() = _githubProperties

    // Job for coroutine
    private var viewModelJob = Job()
    // New coroutine
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Fetch data on start
    init {
        _status.value = GithubApiStatus.LOADING
        getGithubRealStateProperties()
    }

    // Get async data from JSON
    private fun getGithubRealStateProperties() {
        coroutineScope.launch {

            val getGithubPropertiesDeferred = GithubApi.retrofitService.getGithubProperties()

            try {
                githubListResult = getGithubPropertiesDeferred.await().distinctBy { it.id }
                _status.value = GithubApiStatus.DONE
                _githubProperties.value = githubListResult
                Log.d("PROPERTIES", githubListResult.toString())

            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = GithubApiStatus.ERROR
                _githubProperties.value = ArrayList()
            }
        }
    }

    // Cancel job at end
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
