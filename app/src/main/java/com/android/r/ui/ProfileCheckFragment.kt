package com.android.r.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentProfileCheckBinding
import com.android.r.model.Car
import com.android.r.model.Rent
import com.android.r.model.RentInfo
import com.android.r.viewmodel.CustomerViewModel
import com.android.r.viewmodel.RentViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime

class ProfileCheckFragment : BaseFragment<FragmentProfileCheckBinding>(R.layout.fragment_profile_check) {

    val customerViewModel : CustomerViewModel by viewModel()
    val rentViewModel : RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        initAfterBinding()
    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        val rentInfo = arguments?.getSerializable("rent") as Rent
        binding.tvPcId.text = rentInfo.renterId

        customerViewModel.getCustomerById(binding.tvPcId.text.toString())

        customerViewModel.customerLiveData.observe(this, { itemList ->
            binding.tvPcGrade.text = itemList.get(0).gradeAvg.toString()
            binding.tvPcPhonenum.text = itemList.get(0).phone
            binding.tvPcLocation.text = itemList.get(0).address

        })


        //문자
        binding.btnMsg.setOnClickListener {
            val inputPhoneNum = binding.tvPcPhonenum.text
            val myUri = Uri.parse("smsto:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_SENDTO, myUri)

            startActivity(myIntent)
        }


        //전화
        binding.btnCalling.setOnClickListener {

            // 어디에 전화를 걸건지 text 정보 받기
            val input = binding.tvPcPhonenum.text
            // Uri를 이용해서 정보 저장
            val myUri = Uri.parse("tel:${input}")
            // 전환할 정보 설정 - ACTION_DIAL
            val myIntent = Intent(Intent.ACTION_DIAL, myUri)
            // 이동
            startActivity(myIntent)

        }


        binding.btnReserveApproval.setOnClickListener {
            val rentInfo = arguments?.getSerializable("rent") as Rent

            rentViewModel.updateRentInfo(
                RentInfo(
                    rentInfo.rentId, rentInfo.renterId, rentInfo.carInfo.carNumber,
                    LocalDateTime.parse(rentInfo.startTime, org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    LocalDateTime.parse(rentInfo.endTime, org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    "2" , 0.0.toFloat(),  ""
                )
            )

            navController.navigate(R.id.action_profileCheckFragment_to_myCarFragment)
        }
        binding.btnReserveRefuse.setOnClickListener {
            navController.navigate(R.id.action_profileCheckFragment_to_myCarFragment)
        }
    }

}