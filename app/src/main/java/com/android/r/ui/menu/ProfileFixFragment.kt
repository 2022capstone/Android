package com.android.r.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileFixBinding
import com.android.r.model.Customer
import com.android.r.viewmodel.CustomerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFixFragment : BaseFragment<FragmentProfileFixBinding>(R.layout.fragment_profile_fix) {

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

            if(binding.etvProfilefixName.text.isEmpty() || binding.etpProfilefixPhone.text.isEmpty() || binding.etvProfilefixRegion.text.isEmpty()){
                Toast.makeText(requireContext(), "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
            }else {
                customerViewModel.updateCustomer(
                    Customer(
                        binding.tvProfilefixId.text.toString(),
                        binding.etvProfilefixName.text.toString(),
                        binding.etpProfilefixPhone.text.toString(),
                        binding.etvProfilefixRegion.text.toString(),
                        binding.tvProfilefixLicense.text.toString(),
                        binding.tvProfilefixGrade.text.toString().toFloat()
                    )
                )
                navController.navigate(R.id.action_profileFixFragment_to_profileFragment)
            }
        }


        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            navController.navigate(R.id.action_profileFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        super.initAfterBinding()
    }

}