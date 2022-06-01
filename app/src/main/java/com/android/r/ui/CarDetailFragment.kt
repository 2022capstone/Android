package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarDetailBinding
import com.bumptech.glide.Glide

class CarDetailFragment : BaseFragment<FragmentCarDetailBinding>(R.layout.fragment_car_detail) {

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

        Glide.with(context!!)
            .load(arguments?.getString("image"))
            .into(binding.ivDetailImage)
        binding.tvDetailNumber.text = arguments?.getString("number") ?: "number"
        binding.tvDetailModel.text = arguments?.getString("model") ?: "model"
        binding.tvDetailSeater.text = arguments?.getString("seater") ?: "seater"
        binding.tvDetailLocation.text = arguments?.getString("location") ?: "location"
        binding.tvDetailStarttime.text = arguments?.getString("starttime") ?: "starttime"
        binding.tvDetailEndtime.text = arguments?.getString("endtime") ?: "endtime"

    }

}