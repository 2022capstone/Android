package com.android.r.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileBinding
import com.android.r.viewmodel.CustomerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val customerViewModel : CustomerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {

        binding.btnFix.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_profileFixFragment)
        }

        customerViewModel.getCustomerById("nyh710")

        customerViewModel.customerLiveData.observe(this, { itemList->
            binding.tvProfileName.text = itemList.get(0).name
            binding.tvProfileId.text = itemList.get(0).id
            binding.tvProfilePhone.text = itemList.get(0).phone
            binding.tvProfileRegion.text = itemList.get(0).address
            binding.tvProfileLicense.text = itemList.get(0).licenseNum
            binding.tvProfileGrade.text = itemList.get(0).gradeAvg.toString()

        })



        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_profileFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        super.initAfterBinding()
    }
}