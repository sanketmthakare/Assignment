package com.example.assignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.assignment.api.RepoApi
import com.example.assignment.data.TrendingRepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository(var repoApi: RepoApi, var isLoading: MutableLiveData<Boolean>) {
    val repoModel = MutableLiveData<TrendingRepoModel>()
    val erroMessage = MutableLiveData<String>()

    fun getRepoDataApi(): MutableLiveData<TrendingRepoModel> {
        isLoading.postValue(true)
        repoApi.getTrendingRepos("language:kotlin", "starts", "desc")
            .enqueue(object : Callback<TrendingRepoModel> {
                override fun onResponse(
                    call: Call<TrendingRepoModel>,
                    response: Response<TrendingRepoModel>
                ) {
                    isLoading.postValue(false)
                    if (response.isSuccessful)
                        repoModel.postValue(response.body())
                }

                override fun onFailure(call: Call<TrendingRepoModel>, t: Throwable) {
                    isLoading.postValue(false)
                    erroMessage.postValue(t.message)
                }

            })
        return repoModel
    }
}