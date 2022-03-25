package com.android.r.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileFixBinding

class ProfileFixFragment : BaseFragment<FragmentProfileFixBinding>(R.layout.fragment_profile_fix) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        binding.btnFixDone.setOnClickListener {
            navController.navigate(R.id.action_profileFixFragment_to_profileFragment)
        }
    }

}