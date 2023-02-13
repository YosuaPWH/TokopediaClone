package com.yosuahaloho.tokopediaclone.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.yosuahaloho.tokopediaclone.NavigationActivity
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
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

    private fun setData() {
        binding.btnMasuk.setOnClickListener {
            login()
        }

        binding.edtPassword.addTextChangedListener {
            binding.btnMasuk.isEnabled = binding.edtPassword.text!!.isNotEmpty()
                    && binding.edtEmail.text!!.isNotEmpty()
            val layoutParent = binding.edtPassword.parent.parent as TextInputLayout
            layoutParent.errorIconDrawable = null
            layoutParent.error = null
        }

        binding.edtEmail.addTextChangedListener {
            binding.btnMasuk.isEnabled = binding.edtPassword.text!!.isNotEmpty()
                    && binding.edtEmail.text!!.isNotEmpty()
        }
    }

    private fun login() {
        viewModel.login(
            this,
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        ).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this, "Selamat datang ${it.data?.name}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, NavigationActivity::class.java))
                }
                State.LOADING -> {
                    binding.pb.visibility = View.VISIBLE
                }
                State.ERROR -> {
                    binding.pb.visibility = View.GONE
                    val layoutParent = binding.edtPassword.parent.parent as TextInputLayout
                    layoutParent.error = it.message
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}