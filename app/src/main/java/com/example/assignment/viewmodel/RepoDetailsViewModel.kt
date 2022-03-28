package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.api.RepoApi
import com.example.assignment.data.details.RepoDetailsModel
import com.example.assignment.repository.RepoDetailRepositiory

class RepoDetailsViewModel(repoApi: RepoApi, ownerName: String, repoName: String) : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var repoDetailsResponse =
        RepoDetailRepositiory(repoApi, ownerName, repoName, isLoading).getRepoDetails()

    fun getRepoDetails(): LiveData<RepoDetailsModel> {
        return repoDetailsResponse
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }
}