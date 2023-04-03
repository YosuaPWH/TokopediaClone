package com.yosuahaloho.tokopediaclone.core.data.repository

import android.util.Log
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.ErrorResponse
import kotlinx.coroutines.flow.flow

class AppRepository(
    val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    fun login(loginRequest: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(loginRequest).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Log.d("AppRepository-Login", "body: $body")
                    emit(Resource.success(user))
                } else {
                    val error =
                        Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(listOf(error.message), null))
                }
            }
        } catch (e: Exception) {
            Log.d("AppRepository-Login", "login: error: $e")
            emit(Resource.error(listOf(e.message!!), null))
        }
    }

    fun register(registerRequest: RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(registerRequest).let {
                if (it.isSuccessful) {
                    val user = it.body()?.data
                    emit(Resource.success(user))
                    Log.d("register", user.toString())
                } else {
                    val error =
                        Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(listOf(error.message), null))
                    Log.d("registerGagal", error.message)
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(listOf(e.message!!), null))
        }
    }

    fun updateProfile(data: UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateProfile(data).let {
                if (it.isSuccessful) {
                    val user = it.body()?.data
                    emit(Resource.success(user))
                } else {
                    val error =
                        Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(listOf(error.message), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(listOf(e.message!!), null))
        }
    }
}