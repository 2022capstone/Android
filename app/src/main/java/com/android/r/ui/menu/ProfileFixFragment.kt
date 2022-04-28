package com.android.r.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileFixBinding
import com.android.r.viewmodel.CustomerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFixFragment : BaseFragment<FragmentProfileFixBinding>(R.layout.fragment_profile_fix) {

    private val customerViewModel : CustomerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        customerViewModel.getCustomerById("nyh710")
        customerViewModel.customerLiveData.observe(this, { itemList->
            binding.etvProfilefixName.hint = itemList.get(0).name
            binding.tvProfilefixId.text = itemList.get(0).id
            binding.etpProfilefixPhone.hint = itemList.get(0).phone
            binding.etvProfilefixRegion.hint = itemList.get(0).address
            binding.tvProfilefixLicense.text = itemList.get(0).licenseNum
            binding.tvProfilefixGrade.text = itemList.get(0).gradeAvg.toString()
        })

        binding.btnFixDone.setOnClickListener {
            navController.navigate(R.id.action_profileFixFragment_to_profileFragment)
        }
    }

}