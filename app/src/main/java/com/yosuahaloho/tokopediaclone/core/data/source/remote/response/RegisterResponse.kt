package com.yosuahaloho.tokopediaclone.core.data.source.remote.response

import com.yosuahaloho.tokopediaclone.core.data.source.model.User

data class RegisterResponse(
    val status: Int,
    val message: String,
    val data: User?
)