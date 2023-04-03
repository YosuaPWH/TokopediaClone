package com.yosuahaloho.tokopediaclone.core.data.source.remote.network

import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.LoginResponse
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
    ) : Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body register: RegisterRequest
    ) : Response<RegisterResponse>

    @PUT("update-profile/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body updateProfile: UpdateProfileRequest
    ) : Response<LoginResponse>
}