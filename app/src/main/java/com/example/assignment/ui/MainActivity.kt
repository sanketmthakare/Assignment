package com.example.assignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.AssignmentApp
import com.example.assignment.R
import com.example.assignment.viewmodel.RepoViewModel
import com.example.assignment.adapter.RepoAdapter
import com.example.assignment.api.RepoApi
import com.example.assignment.data.TrendingRepoModel
import com.example.assignment.di.MainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity(), RepoAdapter.ItemClickListener {

    lateinit var repoViewModel: RepoViewModel
    lateinit var rvRepoList: RecyclerView
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var repoApi: RepoApi

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as AssignmentApp).appComponent.inject(this)
        rvRepoList = findViewById(R.id.rv_repoList)
        progressBar = findViewById(R.id.progress)
        rvRepoList.layoutManager = LinearLayoutManager(this)
        val repoAdapter = RepoAdapter(this)
        rvRepoList.adapter = repoAdapter
        repoViewModel = ViewModelProvider(this, mainViewModelFactory).get(RepoViewModel::class.java)

        repoViewModel.getRepoList().observe(this, Observer<TrendingRepoModel> {
            repoAdapter.setApiRepoList(it.items)
            repoAdapter.notifyDataSetChanged()
        })
        repoViewModel.isLoading().observe(this, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })
    }

    override fun onItemClick(ownerName: String, repoName: String) {
        startActivity(Intent(this, RepoDetailActivity::class.java).apply {
            this.putExtra(
                "ownerName",
                ownerName
            )
            this.putExtra("repoName", repoName)
        })
    }
}