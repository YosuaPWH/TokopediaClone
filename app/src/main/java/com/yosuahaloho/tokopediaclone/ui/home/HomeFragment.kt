package com.yosuahaloho.tokopediaclone.ui.home

import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.yosuahaloho.tokopediaclone.R
import com.yosuahaloho.tokopediaclone.databinding.FragmentHomeBinding
import com.yosuahaloho.tokopediaclone.ui.home.adapter.SlideImageViewAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        setData()
        ubahData()
        setUpViewPager()
        return root
    }

    private fun setUpViewPager() {
        val imageList = listOf(
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
        )
        val ddw = SlideImageViewAdapter()
        ddw.addItemImage(imageList)
        binding.vpSlideHome.adapter = ddw
        binding.vpSlideHome.setPadding(40, 0, 40, 0)
    }

    private fun setData() {
    }

    fun ubahData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}