package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarRegisterBinding

class CarRegisterFragment : BaseFragment<FragmentCarRegisterBinding>(R.layout.fragment_car_register) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarRegisterBinding.inflate(inflater, container, false)

        binding.btnCarRegister.setOnClickListener {
            navController.navigate(R.id.action_carRegisterFragment_to_myCarFragment)
        }

        return binding.root
    }

}