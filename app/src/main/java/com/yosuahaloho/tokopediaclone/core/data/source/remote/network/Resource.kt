package com.yosuahaloho.tokopediaclone.core.data.source.remote.network

data class Resource<out T>(val state: State, val data: T?, val message: List<String>?) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(State.SUCCESS, data, null)
        }

        fun <T> error(msg: List<String>, data: T?): Resource<T> {
            return Resource(State.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(State.LOADING, data, null)
        }
    }
}