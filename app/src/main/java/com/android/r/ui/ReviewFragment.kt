package com.android.r.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentReviewBinding
import com.android.r.model.Customer
import com.android.r.model.Rent
import com.android.r.model.RentInfo
import com.android.r.viewmodel.CustomerViewModel
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    val rentViewModel : RentViewModel by viewModel()
    val customerViewModel : CustomerViewModel by viewModel()

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

        customerViewModel.getCustomerById(rentInfo.renterId)
        customerViewModel.customerLiveData.observe(this, { itemList->
            binding.tvReviewName.text = itemList.get(0).name
            binding.tvReviewGrade.text = itemList.get(0).gradeAvg.toString()
            //문자
            binding.btnMessage.setOnClickListener {
                val inputPhoneNum = itemList.get(0).phone
                val myUri = Uri.parse("smsto:${inputPhoneNum}")
                val myIntent = Intent(Intent.ACTION_SENDTO, myUri)

                startActivity(myIntent)
            }
            //전화
            binding.btnCall.setOnClickListener {

                // 어디에 전화를 걸건지 text 정보 받기
                val input = itemList.get(0).phone
                // Uri를 이용해서 정보 저장
                val myUri = Uri.parse("tel:${input}")
                // 전환할 정보 설정 - ACTION_DIAL
                val myIntent = Intent(Intent.ACTION_DIAL, myUri)
                // 이동
                startActivity(myIntent)

            }
        })

        binding.tvReviewStarttime.text = rentInfo.startTime
        binding.tvReviewEndtime.text = rentInfo.endTime
        binding.tvReviewModel.text = rentInfo.carInfo.carModel
        binding.tvReviewCarnum.text = rentInfo.carInfo.carNumber
        Glide.with(context!!)
            .load(rentInfo.carInfo.carImage)
            .into(binding.ivReviewCar)

        binding.btnReturnApproval.setOnClickListener {
            navController.navigate(R.id.action_reviewFragment_to_myCarFragment)
        }

        binding.btnReturnApproval.setOnClickListener {
            rentViewModel.updateRentInfo(
                RentInfo(
                    rentInfo.rentId, rentInfo.renterId, rentInfo.carInfo.carNumber,
                    LocalDateTime.parse(rentInfo.startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    LocalDateTime.parse(rentInfo.endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    "7" , binding.ratingBar.rating,  binding.etReview.text.toString()
                )
            )

            navController.navigate(R.id.action_reviewFragment_to_myCarFragment)
        }
    }

}