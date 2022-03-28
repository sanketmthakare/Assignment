package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.api.RepoApi
import com.example.assignment.data.TrendingRepoModel
import com.example.assignment.repository.RepoRepository

class RepoViewModel(private val repo: RepoApi) : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    private var repoModel = RepoRepository(repo, isLoading).getRepoDataApi()

    fun getRepoList(): LiveData<TrendingRepoModel> {
        return repoModel
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }
}