package com.yosuahaloho.tokopediaclone.ui.updateProfile

import androidx.lifecycle.ViewModel
import com.yosuahaloho.tokopediaclone.core.data.repository.ImplAppRepository
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class UpdateProfileViewModel(private val repo: ImplAppRepository) : ViewModel() {

    fun updateProfile(data: UpdateProfileRequest) = repo.updateProfile(data)

    fun uploadImage(id: Int, fileImage: MultipartBody.Part) = repo.uploadImage(id, fileImage)


}