package com.yosuahaloho.tokopediaclone.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yosuahaloho.tokopediaclone.NavigationActivity
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.yosuahaloho.tokopediaclone.databinding.ActivityLoginBinding
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import com.yosuahaloho.tokopediaclone.util.UserPrefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.btnMasuk.setOnClickListener {
            binding.edtEmail.getEditTextLayout().error = null
            binding.edtPassword.getEditTextLayout().error = null
            login()
        }

        binding.edtPassword.addTextChangedListener {
            binding.btnMasuk.isEnabled = binding.edtPassword.text!!.isNotBlank()
                    && binding.edtEmail.text!!.isNotBlank()
            val layoutParent = binding.edtPassword.parent.parent as TextInputLayout
            layoutParent.errorIconDrawable = null
            layoutParent.error = null
        }

        binding.edtEmail.addTextChangedListener {
            binding.btnMasuk.isEnabled = binding.edtPassword.text!!.isNotBlank()
                    && binding.edtEmail.text!!.isNotBlank()
            binding.edtEmail.getEditTextLayout().error = null
        }

        binding.gotoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val loginRequest = LoginRequest(
            email = binding.edtEmail.text.toString(),
            password = binding.edtPassword.text.toString()
        )

        viewModel.login(
            loginRequest
        ).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    val loginPref = LoginPrefs(this)
                    val userPref = UserPrefs(this)

                    loginPref.setIsLogin(true)
                    userPref.setUser(it.data)
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this, "Selamat datang ${it.data?.name}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, NavigationActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Log.d("Login User", UserPrefs(this).getUser().toString())
                }
                State.LOADING -> {
                    binding.pb.visibility = View.VISIBLE
                }
                State.ERROR -> {
                    binding.pb.visibility = View.GONE
                    if (it.message.toString() == "User tidak ditemukan") {
                        binding.edtEmail.getEditTextLayout().error = it.message.toString()
                    } else {
                        binding.edtPassword.getEditTextLayout().error = it.message.toString()
                    }
                    Toast.makeText(this, it.message?.get(0).toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun TextInputEditText.getEditTextLayout() : TextInputLayout {
        return this.parent.parent as TextInputLayout
    }


}