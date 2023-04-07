package com.yosuahaloho.tokopediaclone.core.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.ErrorResponse
import okhttp3.MultipartBody

class ImplAppRepository(
    val local: LocalDataSource,
    private val remote: RemoteDataSource
) : AppRepository {

    override fun login(loginRequest: LoginRequest) = liveData {
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
                    emit(Resource.error(error.message, null))
                }
            }
        } catch (e: Exception) {
            Log.d("AppRepository-Login", "login: error: $e")
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    override fun register(registerRequest: RegisterRequest) = liveData {
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
                    emit(Resource.error(error.message, null))
                    Log.d("registerGagal", error.message)
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    override fun updateProfile(data: UpdateProfileRequest) = liveData {
        emit(Resource.loading(null))
        try {
            remote.updateProfile(data).let {
                if (it.isSuccessful) {
                    val user = it.body()?.data
                    emit(Resource.success(user))
                } else {
                    val error =
                        Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(error.message, null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    override fun uploadImage(id: Int, fileImage: MultipartBody.Part?) = liveData {
        emit(Resource.loading(null))
        try {
            remote.uploadImage(id, fileImage).let {
                if (it.isSuccessful) {
                    val data = it.body()?.data
                    emit(Resource.success(data))
                } else {
                    val error =
                        Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(error.message, null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
}