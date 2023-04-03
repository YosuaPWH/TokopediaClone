package com.yosuahaloho.tokopediaclone.ui.updateProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosuahaloho.tokopediaclone.core.data.repository.AppRepository
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest

class UpdateProfileViewModel(private val repo: AppRepository) : ViewModel() {

    fun updateProfile(data: UpdateProfileRequest) = repo.updateProfile(data).asLiveData()

}