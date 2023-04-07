package com.yosuahaloho.tokopediaclone.util

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object Extension {

    fun String?.getInisial(): String {
        if (this.isNullOrEmpty()) return ""
        val arr = this.split(" ")
        if (arr.isEmpty()) return this
        var inisial = arr[0].substring(0, 1)
        if (arr.size > 1) {
            inisial += arr[1].substring(0, 1)
        }
        return inisial.uppercase()
    }

    fun File?.intoMultipartBody(name: String = "image"): MultipartBody.Part? {
        if (this == null) return null
        val file: RequestBody = this.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, this.name, file)
    }
}