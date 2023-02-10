package com.yosuahaloho.tokopediaclone.core.data.repository

import android.util.Log
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.ErrorResponse
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.LoginResponse
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, private val remote: RemoteDataSource) {

    fun login(username: String, password: String) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(username, password).let {
                val body = it.body()
                if (it.isSuccessful) {
                    Log.d("AppRepository-Login", "body: " + body.toString())
                    emit(Resource.success(body?.data))
                } else {
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(error.message, null))
                }
            }
        } catch (e: Exception) {
            Log.d("AppRepository-Login", "login: error: $e")
        }
    }
}