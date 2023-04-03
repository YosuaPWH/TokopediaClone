package com.yosuahaloho.tokopediaclone.ui.updateProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toInvisible
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.databinding.ActivityUpdateProfileBinding
import com.yosuahaloho.tokopediaclone.util.UserPrefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding

    private val updateProfileViewModel: UpdateProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Profile"

        mainButton()
        setData()
    }

    private fun mainButton() {
        binding.btnSimpan.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        val idUser = UserPrefs(this).getUser()?.id!!

        val body = UpdateProfileRequest(
            id = idUser,
            name = binding.edtNama.text.toString(),
            email = binding.edtEmail.text.toString(),
            phone = binding.edtPhone.text.toString()
        )

        updateProfileViewModel.updateProfile(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pbUpdateProfile.visibility = View.GONE
                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                    finish()
                }
                State.ERROR -> {
                    binding.pbUpdateProfile.visibility = View.GONE
                    Toast.makeText(this, "Data gagal diubah", Toast.LENGTH_SHORT).show()
                }
                State.LOADING -> {
                    binding.pbUpdateProfile.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun setData() {
        val user = UserPrefs(this).getUser()
        if (user != null) {
            binding.edtNama.setText(user.name)
            binding.edtEmail.setText(user.email)
            binding.edtPhone.setText(user.phone)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}