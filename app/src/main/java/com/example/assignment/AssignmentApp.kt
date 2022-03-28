package com.example.assignment

import android.app.Application
import com.example.assignment.di.component.AppComponent
import com.example.assignment.di.component.DaggerAppComponent
class AssignmentApp : Application() {
    lateinit var appComponent : AppComponent;

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}