package com.yosuahaloho.tokopediaclone.core.di

import com.yosuahaloho.tokopediaclone.ui.dashboard.DashboardViewModel
import com.yosuahaloho.tokopediaclone.ui.home.HomeViewModel
import com.yosuahaloho.tokopediaclone.ui.keranjang.KeranjangViewModel
import com.yosuahaloho.tokopediaclone.ui.account.AccountViewModel
import com.yosuahaloho.tokopediaclone.ui.auth.AuthViewModel
import com.yosuahaloho.tokopediaclone.ui.updateProfile.UpdateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { DashboardViewModel() }

    viewModel { HomeViewModel() }

    viewModel { AuthViewModel(get()) }

    viewModel { KeranjangViewModel() }

    viewModel { AccountViewModel() }

    viewModel { UpdateProfileViewModel(get()) }
}