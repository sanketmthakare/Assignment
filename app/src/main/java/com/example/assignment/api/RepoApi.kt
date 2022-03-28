package com.example.assignment.api

import com.example.assignment.data.TrendingRepoModel
import com.example.assignment.data.details.RepoDetailsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {

    @GET("search/repositories")
    fun getTrendingRepos(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Call<TrendingRepoModel>

    @GET("repos/{owner}/{repoName}")
    fun getRepoDetails(
        @Path("owner") ownerName: String,
        @Path("repoName") repoName: String
    ): Call<RepoDetailsModel>
}