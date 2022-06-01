package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarDetailBinding
import com.android.r.databinding.FragmentImageDetailBinding
import com.android.r.model.CarImage
import com.android.r.model.Rent
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel


class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(R.layout.fragment_image_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        initAfterBinding()
    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        val imageInfo = arguments?.getString("image")

        Glide.with(context!!)
            .load(imageInfo)
            .into(binding.ivAftercheckCar)

    }
}