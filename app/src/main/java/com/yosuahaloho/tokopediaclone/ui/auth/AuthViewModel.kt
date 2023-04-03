package com.yosuahaloho.tokopediaclone.ui.auth

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosuahaloho.tokopediaclone.core.data.repository.AppRepository
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest

class AuthViewModel(private val repo: AppRepository) : ViewModel() {

    fun login(loginRequest: LoginRequest) = repo.login(loginRequest).asLiveData()

    fun register(registerRequest: RegisterRequest) = repo.register(registerRequest).asLiveData()
}