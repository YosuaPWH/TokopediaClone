package com.yosuahaloho.tokopediaclone.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
import com.yosuahaloho.tokopediaclone.databinding.ActivityRegisterBinding
import com.yosuahaloho.tokopediaclone.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.edtNama.checkEnabledButtonRegister()
        binding.edtEmail.checkEnabledButtonRegister()
        binding.edtPhone.checkEnabledButtonRegister()
        binding.edtPassword.checkEnabledButtonRegister()

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        viewModel.register(
            binding.edtNama.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtPassword.text.toString()
        ).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                State.LOADING -> {
                    binding.pb.visibility = View.VISIBLE
                }
                State.ERROR -> {
                    it.message!!.forEach {err ->
                        if (err.contains("email")) {
                            binding.edtEmail.getEditTextLayout().error = err
                        } else if (err.contains("phone")) {
                            binding.edtPhone.getEditTextLayout().error = err
                        }
                    }
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun TextInputEditText.getEditTextLayout(): TextInputLayout {
        return this.parent.parent as TextInputLayout
    }

    private fun TextInputEditText.checkEnabledButtonRegister() {
        this.addTextChangedListener {
            binding.btnRegister.isEnabled = binding.edtNama.text!!.isNotBlank()
                    && binding.edtEmail.text!!.isNotBlank()
                    && binding.edtPhone.text!!.isNotBlank()
                    && binding.edtPassword.text!!.isNotBlank()
            this.getEditTextLayout().error = null
        }
    }
}