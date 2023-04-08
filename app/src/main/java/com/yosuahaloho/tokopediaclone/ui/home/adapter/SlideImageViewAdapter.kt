package com.yosuahaloho.tokopediaclone.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.yosuahaloho.tokopediaclone.databinding.ItemHomeVpSlideImageBinding

class SlideImageViewAdapter : PagerAdapter() {
    private val imageSlide: ArrayList<Int> = ArrayList()


    override fun getCount(): Int = imageSlide.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    fun addItemImage(image: List<Int>) {
        imageSlide.addAll(image)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding = ItemHomeVpSlideImageBinding.inflate(LayoutInflater.from(container.context), container, false)
        val item = imageSlide[position]

        Glide
            .with(container.rootView)
            .load(item)
            .into(binding.ivImage)

//        return super.instantiateItem(container, position)
        container.addView(binding.root)
        return binding.root
    }


}