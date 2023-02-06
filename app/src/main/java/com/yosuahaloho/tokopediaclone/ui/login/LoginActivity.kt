package com.yosuahaloho.tokopediaclone.ui.login

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yosuahaloho.tokopediaclone.R
import com.yosuahaloho.tokopediaclone.databinding.ActivityLoginBinding
import com.yosuahaloho.tokopediaclone.util.Prefs

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val btnBack: Button = findViewById(R.id.btnBack)
//
//        btnBack.setOnClickListener {
////            val prefs = Prefs(this)
////            prefs.setIsLogin(false)
//            onBackPressed()
//        }
    }
}