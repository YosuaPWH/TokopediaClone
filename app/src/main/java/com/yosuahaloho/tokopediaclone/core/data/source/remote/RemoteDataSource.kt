package com.yosuahaloho.tokopediaclone.core.data.source.remote

import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.ApiService

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(username: String, password: String) = api.login(username, password)

}