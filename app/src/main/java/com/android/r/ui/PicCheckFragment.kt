package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentPicCheckBinding


class PicCheckFragment : BaseFragment<FragmentPicCheckBinding>(R.layout.fragment_pic_check) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        binding.btnApprove.setOnClickListener {
            navController.navigate(R.id.action_picCheckFragment_to_myCarFragment)
        }
        binding.btnRefuse.setOnClickListener {
            navController.navigate(R.id.action_picCheckFragment_to_myCarFragment)
        }
    }


}