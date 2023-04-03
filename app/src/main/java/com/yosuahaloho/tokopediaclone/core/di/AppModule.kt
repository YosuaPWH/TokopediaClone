package com.yosuahaloho.tokopediaclone.core.di

import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.ApiConfig
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import com.yosuahaloho.tokopediaclone.util.UserPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.instance }

    single { RemoteDataSource(get()) }

    single { LocalDataSource() }
}

