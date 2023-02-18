package com.yosuahaloho.tokopediaclone.ui.register

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosuahaloho.tokopediaclone.core.data.repository.AppRepository

class RegisterViewModel(private val repo: AppRepository) : ViewModel() {

    fun register(
        nama: String,
        email: String,
        phone: String,
        password: String
    ) = repo.register(nama, email, phone, password).asLiveData()
}