package com.yosuahaloho.tokopediaclone.ui.updateProfile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.databinding.ActivityUpdateProfileBinding
import com.yosuahaloho.tokopediaclone.util.Extension.getInisial
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
        binding.edtNama.checkChangedData()
        binding.edtEmail.checkChangedData()
        binding.edtPhone.checkChangedData()

        binding.btnSimpan.setOnClickListener {
            updateProfile()
        }

        binding.ivProfil.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    pickPhoto.launch(intent)
                }
        }
    }

    private val pickPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
        val resultCode = res.resultCode
        val data = res.data

        when (resultCode) {
            Activity.RESULT_OK -> {
                Glide
                    .with(this)
                    .load(data?.data!!)
                    .centerCrop()
                    .into(binding.ivProfil)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateProfile() {
        val currentUser = UserPrefs(this).getUser()

        val body = UpdateProfileRequest(
            id = currentUser?.id!!,
            name = binding.edtNama.text.toString(),
            email = binding.edtEmail.text.toString(),
            phone = binding.edtPhone.text.toString()
        )

        updateProfileViewModel.updateProfile(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pbUpdateProfile.visibility = View.GONE
                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                    UserPrefs(this).setUser(it.data)
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
            binding.tvInisial.text = user.name.getInisial()
        }
    }

    private fun TextInputEditText.checkChangedData() {
        val user = UserPrefs(this@UpdateProfileActivity).getUser()
        this.addTextChangedListener {
            binding.btnSimpan.isEnabled = binding.edtNama.text.toString() != user?.name ||
                    binding.edtEmail.text.toString() != user.email ||
                    binding.edtPhone.text.toString() != user.phone
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}