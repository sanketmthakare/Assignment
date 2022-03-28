package com.example.assignment.di.module

import com.example.assignment.api.RepoApi
import com.example.assignment.di.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {
    @Singleton
    @Provides
    fun provideMainViewModelFactory(repoApi: RepoApi):MainViewModelFactory{
        return MainViewModelFactory(repoApi)
    }
}