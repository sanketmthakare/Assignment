package com.example.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.AssignmentApp
import com.example.assignment.R
import com.example.assignment.api.RepoApi
import com.example.assignment.di.MainViewModelFactory
import com.example.assignment.viewmodel.RepoDetailsViewModel
import com.example.assignment.viewmodel.RepoViewModel
import javax.inject.Inject

class RepoDetailActivity : AppCompatActivity() {
    lateinit var tvRepoDetails: TextView
    lateinit var repoViewModel: RepoDetailsViewModel
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var repoApi: RepoApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        (applicationContext as AssignmentApp).appComponent.inject(this)
        tvRepoDetails = findViewById(R.id.tv_repo_details)
        progressBar = findViewById(R.id.progress)
        val ownerName = intent.getStringExtra("ownerName").toString()
        val repoName = intent.getStringExtra("repoName").toString()
        repoViewModel =
            ViewModelProvider(this, MainViewModelFactory(repoApi, ownerName, repoName)).get(
                RepoDetailsViewModel::class.java
            )

        repoViewModel.getRepoDetails().observe(this, Observer {
            tvRepoDetails.setText("Repository Details : \n" + it.html_url)
        })

        repoViewModel.isLoading().observe(this, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })
    }
}