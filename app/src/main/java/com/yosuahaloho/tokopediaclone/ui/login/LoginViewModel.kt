package com.yosuahaloho.tokopediaclone.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yosuahaloho.tokopediaclone.core.data.repository.AppRepository

class LoginViewModel(val repo: AppRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun ubahData() {
        _text.postValue("Percobaan yosua")
    }

}