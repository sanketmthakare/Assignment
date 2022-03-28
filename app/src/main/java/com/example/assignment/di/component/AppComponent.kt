package com.example.assignment.di.component

import com.example.assignment.di.module.AppModule
import com.example.assignment.di.module.ViewModelModule
import com.example.assignment.ui.MainActivity
import com.example.assignment.ui.RepoDetailActivity
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: RepoDetailActivity)
}