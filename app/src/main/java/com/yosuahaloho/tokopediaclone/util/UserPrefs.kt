package com.yosuahaloho.tokopediaclone.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.yosuahaloho.tokopediaclone.core.data.source.model.User

class UserPrefs(context: Context) {

    private var sp: SharedPreferences? = null
    private var user = "user"

    init {
        sp = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    }

    fun setUser(data: User?) {
        val gson = Gson()
        sp!!.edit().putString(user, gson.toJson(data)).apply()
    }

    fun getUser(): User? {
        val user = sp!!.getString(user, "")
        return Gson().fromJson(user, User::class.java)
    }

}