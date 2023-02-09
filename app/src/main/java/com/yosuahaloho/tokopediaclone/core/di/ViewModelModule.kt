package com.yosuahaloho.tokopediaclone.core.di

import com.yosuahaloho.tokopediaclone.ui.dashboard.DashboardViewModel
import com.yosuahaloho.tokopediaclone.ui.home.HomeViewModel
import com.yosuahaloho.tokopediaclone.ui.keranjang.KeranjangViewModel
import com.yosuahaloho.tokopediaclone.ui.login.LoginViewModel
import com.yosuahaloho.tokopediaclone.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { DashboardViewModel() }

    viewModel { HomeViewModel() }

    viewModel { KeranjangViewModel() }

    viewModel { NotificationsViewModel() }
}