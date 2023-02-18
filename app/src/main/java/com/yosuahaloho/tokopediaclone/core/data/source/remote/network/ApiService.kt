package com.yosuahaloho.tokopediaclone.core.data.source.remote.network

import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.LoginResponse
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") nama: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ) : Response<RegisterResponse>
}