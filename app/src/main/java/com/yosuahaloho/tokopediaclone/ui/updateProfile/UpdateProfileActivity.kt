package com.yosuahaloho.tokopediaclone.ui.updateProfile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.yosuahaloho.tokopediaclone.core.data.source.model.User
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.Resource
import com.yosuahaloho.tokopediaclone.core.data.source.remote.network.State
import com.yosuahaloho.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.yosuahaloho.tokopediaclone.databinding.ActivityUpdateProfileBinding
import com.yosuahaloho.tokopediaclone.util.Constants
import com.yosuahaloho.tokopediaclone.util.Extension.getInisial
import com.yosuahaloho.tokopediaclone.util.Extension.intoMultipartBody
import com.yosuahaloho.tokopediaclone.util.UserPrefs
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    private val updateProfileViewModel: UpdateProfileViewModel by viewModel()
    private var fileImage: File? = null

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

    private fun setData() {
        val user = UserPrefs(this).getUser()
        if (user != null) {
            binding.edtNama.setText(user.name)
            binding.edtEmail.setText(user.email)
            binding.edtPhone.setText(user.phone)

            if (user.image != null) {
                binding.tvInisial.text = null
                Glide
                    .with(this@UpdateProfileActivity)
                    .load(Constants.STORAGE_USER + user.image)
                    .centerCrop()
                    .into(binding.ivProfil)
            } else {
                binding.tvInisial.text = user.name.getInisial()
            }
        }
    }

    private fun mainButton() {
        binding.edtNama.checkChangedDataForBtnSimpan()
        binding.edtEmail.checkChangedDataForBtnSimpan()
        binding.edtPhone.checkChangedDataForBtnSimpan()

        binding.btnSimpan.setOnClickListener {
            if (fileImage != null) {
                upload(fileImage.intoMultipartBody())
                updateProfile()
            } else {
                updateProfile()
            }
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
        val uri = data?.data

        when (resultCode) {
            Activity.RESULT_OK -> {
                Glide
                    .with(this)
                    .load(uri)
                    .centerCrop()
                    .into(binding.ivProfil)

                fileImage = File(uri?.path ?: "")
                binding.btnSimpan.isEnabled = true
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

        updateProfileViewModel.updateProfile(body).observeRemoteData(
            successMethod = {
                Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                UserPrefs(this).setUser(it)
                finish()
            },
            errorMethod = {
                Toast.makeText(this, "Data gagal diubah", Toast.LENGTH_SHORT).show()
                Log.d("Error Upload Profile", it ?: "Error")
            }
        )
    }

    private fun upload(fileMultipartBody: MultipartBody.Part?) {
        val currentUserId = UserPrefs(this).getUser()?.id!!

        updateProfileViewModel.uploadImage(currentUserId, fileMultipartBody!!).observeRemoteData(
            successMethod = {
                binding.pbUpdateProfile.visibility = View.GONE
                Toast.makeText(this, "Upload Foto berhasil", Toast.LENGTH_SHORT).show()
                UserPrefs(this).setUser(it)
            },
            errorMethod = {
                Toast.makeText(this, "Kesalahan upload Foto", Toast.LENGTH_SHORT).show()
                Log.d("Error Upload Image", it ?: "Error")
            }
        )
    }

    private fun LiveData<Resource<User>>.observeRemoteData(
        successMethod: (User) -> Unit,
        errorMethod: (String?) -> Unit
    ) {
        this.observe(this@UpdateProfileActivity) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pbUpdateProfile.visibility = View.GONE
                    successMethod(it.data!!)
                }
                State.ERROR -> {
                    binding.pbUpdateProfile.visibility = View.GONE
                    errorMethod(it.message)
                }
                State.LOADING -> {
                    binding.pbUpdateProfile.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun TextInputEditText.checkChangedDataForBtnSimpan() {
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