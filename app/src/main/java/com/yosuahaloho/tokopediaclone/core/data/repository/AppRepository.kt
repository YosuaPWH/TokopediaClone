package com.yosuahaloho.tokopediaclone.core.data.repository

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.response.ErrorResponse
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import com.yosuahaloho.tokopediaclone.util.UserPrefs
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, private val remote: RemoteDataSource) {

    fun login(activity: Activity, email: String, password: String) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(email, password).let {
                val pref = LoginPrefs(activity)
                val userPref = UserPrefs(activity)
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    pref.setIsLogin(true)
                    userPref.setUser(user)
                    Log.d("AppRepository-Login", "body: $body")
                    emit(Resource.success(user))
                } else {
                    pref.setIsLogin(false)
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(listOf(error.message), null))
                }
            }
        } catch (e: Exception) {
            Log.d("AppRepository-Login", "login: error: $e")
            emit(Resource.error(listOf(e.message!!), null))
        }
    }

    fun register(nama: String, email: String, phone: String, password: String) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(nama, email, phone, password).let {
                if (it.isSuccessful) {
                    val user = it.body()?.data
                    emit(Resource.success(user))
                    Log.d("register", user.toString())
                } else {
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Resource.error(listOf(error.message), null))
                    Log.d("registerGagal", error.message.toString())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(listOf(e.message!!), null))
        }
    }
}