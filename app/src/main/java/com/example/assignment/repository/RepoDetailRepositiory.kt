package com.example.assignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.assignment.api.RepoApi
import com.example.assignment.data.details.RepoDetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDetailRepositiory(
    val repoApi: RepoApi,
    val ownerName: String,
    val repoName: String,
    var isLoading: MutableLiveData<Boolean>
) {
    var repoDetailsResponse = MutableLiveData<RepoDetailsModel>()
    var errorMessage = MutableLiveData<String>()

    fun getRepoDetails(): MutableLiveData<RepoDetailsModel> {
        isLoading.postValue(true)
        repoApi.getRepoDetails(ownerName, repoName).enqueue(object : Callback<RepoDetailsModel> {
            override fun onResponse(
                call: Call<RepoDetailsModel>,
                response: Response<RepoDetailsModel>
            ) {
                isLoading.postValue(false)
                if (response.isSuccessful)
                    repoDetailsResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<RepoDetailsModel>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue(t.message)
            }
        })
        return repoDetailsResponse
    }
}