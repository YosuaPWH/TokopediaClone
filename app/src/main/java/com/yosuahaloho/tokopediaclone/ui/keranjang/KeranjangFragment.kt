package com.yosuahaloho.tokopediaclone.ui.keranjang

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yosuahaloho.tokopediaclone.NavigationActivity
import com.yosuahaloho.tokopediaclone.databinding.FragmentKeranjangBinding
import com.yosuahaloho.tokopediaclone.util.LoginPrefs
import com.yosuahaloho.tokopediaclone.util.UserPrefs

class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, 
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(KeranjangViewModel::class.java)

        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textKeranjang
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            val pref = LoginPrefs(requireContext())

            pref.setIsLogin(false)

            val userPref = UserPrefs(requireContext())
            userPref.setUser(null)

            startActivity(Intent(context, NavigationActivity::class.java))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}