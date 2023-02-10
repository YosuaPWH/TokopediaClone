package com.yosuahaloho.tokopediaclone.core.data.source.remote.response

import com.yosuahaloho.tokopediaclone.core.data.source.model.User

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: User?
)
