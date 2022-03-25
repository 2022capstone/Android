package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentReviewBinding

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        binding.btnReturnApproval.setOnClickListener {
            navController.navigate(R.id.action_reviewFragment_to_myCarFragment)
        }
    }

}