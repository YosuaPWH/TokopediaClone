package com.yosuahaloho.tokopediaclone.core.data.repository

import com.yosuahaloho.tokopediaclone.core.data.source.local.LocalDataSource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.RemoteDataSource

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

}