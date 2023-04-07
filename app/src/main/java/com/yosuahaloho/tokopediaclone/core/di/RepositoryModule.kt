package com.yosuahaloho.tokopediaclone.core.di

import com.yosuahaloho.tokopediaclone.core.data.repository.ImplAppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ImplAppRepository(get(), get()) }
}