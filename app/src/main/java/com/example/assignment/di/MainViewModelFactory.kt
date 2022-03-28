package com.example.assignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.api.RepoApi
import com.example.assignment.viewmodel.RepoDetailsViewModel
import com.example.assignment.viewmodel.RepoViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val api: RepoApi) : ViewModelProvider.Factory {

    private lateinit var param1: String
    private lateinit var param2: String

    constructor(repoApi: RepoApi, param1: String, param2: String) : this(repoApi) {
        this.param1 = param1
        this.param2 = param2
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(api) as T
        } else if (modelClass.isAssignableFrom(RepoDetailsViewModel::class.java)) {
            return RepoDetailsViewModel(api, param1, param2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}