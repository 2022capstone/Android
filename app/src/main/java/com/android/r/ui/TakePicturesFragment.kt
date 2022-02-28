package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.android.r.databinding.FragmentTakePicturesBinding

class TakePicturesFragment : BaseFragment<FragmentTakePicturesBinding>(R.layout.fragment_take_pictures) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTakePicturesBinding.inflate(inflater, container, false)

        binding.btnTakeFinish.setOnClickListener {
            navController.navigate(R.id.action_takePicturesFragment_to_usageDetailFragment)
        }

        return binding.root
    }
}