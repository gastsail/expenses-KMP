package com.myapplication

import android.app.Application
import di.commonModule
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ExpensesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@ExpensesApplication)
            modules(commonModule)
        }
    }
}