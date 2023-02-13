package com.yosuahaloho.tokopediaclone.util

import android.content.Context
import android.content.SharedPreferences

class LoginPrefs(context: Context) {

    private var sp: SharedPreferences? = null
    private val login = "login"

    init {
        sp = context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
    }

    fun setIsLogin(value: Boolean) {
        sp!!.edit().putBoolean(login, value).apply()
    }

    fun getIsLogin() : Boolean {
        return sp!!.getBoolean(login, false)
    }
}