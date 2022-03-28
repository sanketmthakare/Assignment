package com.example.assignment.data

data class TrendingRepoModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)