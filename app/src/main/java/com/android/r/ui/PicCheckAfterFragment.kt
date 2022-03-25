package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentPicCheckAfterBinding

class PicCheckAfterFragment : BaseFragment<FragmentPicCheckAfterBinding>(R.layout.fragment_pic_check_after) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        binding.btnReview.setOnClickListener {
            navController.navigate(R.id.action_picCheckAfterFragment_to_reviewFragment)
        }
    }

}