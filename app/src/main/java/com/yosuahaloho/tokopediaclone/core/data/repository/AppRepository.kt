package com.yosuahaloho.tokopediaclone.core.data.repository

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.ErrorResponse
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, private val remote: RemoteDataSource) {

    fun login(activity: Activity, username: String, password: String) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(username, password).let {
                val pref = LoginPrefs(activity)
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    pref.setIsLogin(true)
                    Log.d("AppRepository-Login", "body: " + body.toString())
                    emit(Resource.success(user))
                } else {
                    pref.setIsLogin(false)
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(error.message, null))
                }
            }
        } catch (e: Exception) {
            Log.d("AppRepository-Login", "login: error: $e")
            emit(Resource.error(e.message ?: "Terjadi kesalahan", null))
        }
    }
}