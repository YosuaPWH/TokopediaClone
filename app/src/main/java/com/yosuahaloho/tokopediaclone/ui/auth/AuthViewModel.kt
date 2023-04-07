package com.yosuahaloho.tokopediaclone.ui.auth

import androidx.lifecycle.ViewModel
import com.yosuahaloho.tokopediaclone.core.data.repository.ImplAppRepository
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest

class AuthViewModel(private val repo: ImplAppRepository) : ViewModel() {

    fun login(loginRequest: LoginRequest) = repo.login(loginRequest)

    fun register(registerRequest: RegisterRequest) = repo.register(registerRequest)
}