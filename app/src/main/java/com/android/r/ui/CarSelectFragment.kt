package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarSelectBinding
import com.android.r.databinding.FragmentStart2Binding
import com.bumptech.glide.Glide

class CarSelectFragment : BaseFragment<FragmentCarSelectBinding>(R.layout.fragment_car_select) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        Glide.with(context!!)
            .load(arguments?.getString("image"))
            .into(binding.ivCarimg)
        binding.tvCarnum.text = arguments?.getString("number") ?: "number"
        binding.tvModel.text = arguments?.getString("model") ?: "model"
        binding.tvSeater.text = arguments?.getString("seater") ?: "seater"


        binding.btnBook.setOnClickListener {
            navController.navigate(R.id.action_carSelectFragment_to_start2Fragment)
        }

        //calendar
        binding.btnStarttime.setOnClickListener {

        }

        binding.btnEndtime.setOnClickListener {

        }
    }

}