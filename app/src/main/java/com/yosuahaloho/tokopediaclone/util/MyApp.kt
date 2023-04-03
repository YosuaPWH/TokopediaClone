package com.yosuahaloho.tokopediaclone.util

import android.app.Application
import com.yosuahaloho.tokopediaclone.core.di.appModule
import com.yosuahaloho.tokopediaclone.core.di.repositoryModule
import com.yosuahaloho.tokopediaclone.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }

    }
}