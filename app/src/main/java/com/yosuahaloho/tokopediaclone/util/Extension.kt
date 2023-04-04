package com.yosuahaloho.tokopediaclone.util

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
}