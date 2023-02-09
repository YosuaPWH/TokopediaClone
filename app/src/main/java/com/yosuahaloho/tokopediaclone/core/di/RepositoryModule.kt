package com.yosuahaloho.tokopediaclone.core.di

import com.yosuahaloho.tokopediaclone.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}