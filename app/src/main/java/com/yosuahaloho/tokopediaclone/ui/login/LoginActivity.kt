package com.yosuahaloho.tokopediaclone.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yosuahaloho.tokopediaclone.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    fun setData() {
        viewModel.text.observe(this) {
            binding.edtEmail.setText(it)
        };

        binding.btnMasuk.setOnClickListener {
            viewModel.ubahData()
        }
    }
}