package com.yosuahaloho.tokopediaclone.core.data.source.remote

import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.ApiService
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data: LoginRequest) = api.login(data)

    suspend fun register(data: RegisterRequest) = api.register(data)

    suspend fun updateProfile(data: UpdateProfileRequest) = api.updateProfile(data.id, data)

    suspend fun uploadImage(id: Int, fileImage: MultipartBody.Part? = null) = api.uploadImage(id, fileImage)

}