package com.yosuahaloho.tokopediaclone.core.data.repository

import androidx.lifecycle.LiveData
import com.yosuahaloho.tokopediaclone.core.data.source.model.User
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

interface AppRepository {

    fun login(loginRequest: LoginRequest) : LiveData<Resource<User>>

    fun register(registerRequest: RegisterRequest) : LiveData<Resource<User>>

    fun updateProfile(data: UpdateProfileRequest) : LiveData<Resource<User>>

    fun uploadImage(id: Int, fileImage: MultipartBody.Part? = null) : LiveData<Resource<User>>
}