package com.yosuahaloho.tokopediaclone.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yosuahaloho.tokopediaclone.NavigationActivity
import com.yosuahaloho.tokopediaclone.databinding.FragmentAccountBinding
import com.yosuahaloho.tokopediaclone.ui.updateProfile.UpdateProfileActivity
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import com.yosuahaloho.tokopediaclone.util.UserPrefs

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUser()
        setButton()

        return root
    }

    private fun setButton() {
        binding.btnLogout.setOnClickListener {
            val pref = LoginPrefs(requireContext())
            pref.setIsLogin(false)

            val userPref = UserPrefs(requireContext())
            userPref.setUser(null)

            startActivity(Intent(context, NavigationActivity::class.java))
        }

        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(context, UpdateProfileActivity::class.java))
        }
    }


    private fun setUser() {
        val dataUser = UserPrefs(requireContext()).getUser()
        if (dataUser != null) {
            binding.apply {
                tvName.text = dataUser.name
                tvEmail.text = dataUser.email
                tvPhone.text = dataUser.phone
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}