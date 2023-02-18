package com.yosuahaloho.tokopediaclone.core.data.source.remote

import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.ApiService

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(email: String, password: String) = api.login(email, password)

    suspend fun register(nama: String, email: String, phone: String, password: String) = api.register(nama, email, phone, password)

}