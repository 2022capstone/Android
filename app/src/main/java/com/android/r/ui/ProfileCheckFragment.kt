package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileCheckBinding

class ProfileCheckFragment : BaseFragment<FragmentProfileCheckBinding>(R.layout.fragment_profile_check) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileCheckBinding.inflate(inflater, container, false)

        binding.btnReserveApproval.setOnClickListener {
            navController.navigate(R.id.action_profileCheckFragment_to_myCarFragment)
        }
        binding.btnReserveRefuse.setOnClickListener {
            navController.navigate(R.id.action_profileCheckFragment_to_myCarFragment)
        }

        return binding.root
    }
}