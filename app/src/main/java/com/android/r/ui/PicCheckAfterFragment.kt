package com.android.r.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentPicCheckAfterBinding
import com.android.r.model.Rent
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class PicCheckAfterFragment : BaseFragment<FragmentPicCheckAfterBinding>(R.layout.fragment_pic_check_after) {

    val carImageViewModel : CarImageViewModel by viewModel()
    val rentViewModel : RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        val rentInfo = arguments?.getSerializable("rent") as Rent

        carImageViewModel.getCarImageAfterRentByRentId(rentInfo.rentId)

        carImageViewModel.carImageLiveData.observe(this, { item ->

            Glide.with(context!!)
                .load(item.get(0).beforeFrontImage)
                .into(binding.ivBeforeFront)
            Glide.with(context!!)
                .load(item.get(0).beforeBackImage)
                .into(binding.ivBeforeBack)
            Glide.with(context!!)
                .load(item.get(0).beforeDriveFrontImage)
                .into(binding.ivBeforeD)
            Glide.with(context!!)
                .load(item.get(0).beforeDriveBackImage)
                .into(binding.ivBeforeDback)
            Glide.with(context!!)
                .load(item.get(0).beforePassengerFrontImage)
                .into(binding.ivBeforeP)
            Glide.with(context!!)
                .load(item.get(0).beforePassengerBackImage)
                .into(binding.ivBeforePback)
            Glide.with(context!!)
                .load(item.get(0).afterFrontImage)
                .into(binding.ivAfterFront)
            Glide.with(context!!)
                .load(item.get(0).afterBackImage)
                .into(binding.ivAfterBack)
            Glide.with(context!!)
                .load(item.get(0).afterDriveFrontImage)
                .into(binding.ivAfterD)
            Glide.with(context!!)
                .load(item.get(0).afterDriveBackImage)
                .into(binding.ivAfterDback)
            Glide.with(context!!)
                .load(item.get(0).afterPassengerFrontImage)
                .into(binding.ivAfterP)
            Glide.with(context!!)
                .load(item.get(0).afterPassengerBackImage)
                .into(binding.ivAfterPback)
        })



        binding.btnReview.setOnClickListener {
            navController.navigate(R.id.action_picCheckAfterFragment_to_reviewFragment)
        }
    }

}