package com.android.r.ui

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentPicCheckBinding
import com.android.r.model.CarImage
import com.android.r.model.Rent
import com.android.r.model.RentInfo
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule


class PicCheckFragment : BaseFragment<FragmentPicCheckBinding>(R.layout.fragment_pic_check) {

    val carImageViewModel : CarImageViewModel by viewModel()
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

        carImageViewModel.getCarImageBeforeRentByRentId(rentInfo.rentId)

        carImageViewModel.carImageLiveData.observe(this, { item ->

            Glide.with(context!!)
                .load(item.get(0).beforeFrontImage)
                .into(binding.ivFrontCar)
            Glide.with(context!!)
                .load(item.get(0).beforeBackImage)
                .into(binding.ivBackCar)
            Glide.with(context!!)
                .load(item.get(0).beforeDriveFrontImage)
                .into(binding.ivDirver)
            Glide.with(context!!)
                .load(item.get(0).beforeDriveBackImage)
                .into(binding.ivBackDirver)
            Glide.with(context!!)
                .load(item.get(0).beforePassengerFrontImage)
                .into(binding.ivPassenser)
            Glide.with(context!!)
                .load(item.get(0).beforePassengerBackImage)
                .into(binding.ivBackPassenser)
        })


        binding.btnApprove.setOnClickListener {
            val rentInfo = arguments?.getSerializable("rent") as Rent

            rentViewModel.updateRentInfo(
                RentInfo(
                    rentInfo.rentId, rentInfo.renterId, rentInfo.carInfo.carNumber,
                    LocalDateTime.parse(rentInfo.startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    LocalDateTime.parse(rentInfo.endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    "4" , 0.0.toFloat(),  ""
                )
            )

            navController.navigate(R.id.action_picCheckFragment_to_myCarFragment)
        }
        binding.btnRefuse.setOnClickListener {
            val rentInfo = arguments?.getSerializable("rent") as Rent

            rentViewModel.deleteRentInfo(rentInfo.rentId)

            navController.navigate(R.id.action_picCheckFragment_to_myCarFragment)
        }
    }

}