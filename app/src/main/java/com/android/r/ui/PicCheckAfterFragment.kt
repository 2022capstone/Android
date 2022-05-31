package com.android.r.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentPicCheckAfterBinding
import com.android.r.model.Customer
import com.android.r.model.Rent
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.CustomerViewModel
import com.android.r.viewmodel.RentViewModel
import com.android.r.viewmodel.ScratchViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class PicCheckAfterFragment : BaseFragment<FragmentPicCheckAfterBinding>(R.layout.fragment_pic_check_after) {

    val carImageViewModel : CarImageViewModel by viewModel()
    val rentViewModel : RentViewModel by viewModel()
    val customerViewModel : CustomerViewModel by viewModel()
    val scratchViewModel : ScratchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        val isVisible = arguments?.getBoolean("btn")
        if(isVisible!!){
            binding.btnReview.visibility = View.VISIBLE
        } else{
            binding.btnReview.visibility = View.GONE
        }

    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {

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

        scratchViewModel.getScratchInfo(rentInfo.rentId)

        scratchViewModel.scratchLiveData.observe(this, { itemList ->
            binding.tvBeforeFront.text = itemList.beforeFrontCount.toString()
            binding.tvBeforeBack.text = itemList.beforeBackCount.toString()
            binding.tvBeforeD.text = itemList.beforeDriveFrontCount.toString()
            binding.tvBeforeDback.text = itemList.beforeDriveBackCount.toString()
            binding.tvBeforeP.text = itemList.beforePassengerFrontCount.toString()
            binding.tvBeforePback.text = itemList.beforePassengerBackCount.toString()

            binding.tvAfterFront.text = itemList.afterFrontCount.toString()
            binding.tvAfterBack.text = itemList.afterBackCount.toString()
            binding.tvAfterD.text = itemList.afterDriveFrontCount.toString()
            binding.tvAfterDback.text = itemList.afterDriveBackCount.toString()
            binding.tvAfterP.text = itemList.afterPassengerFrontCount.toString()
            binding.tvAfterPback.text = itemList.afterPassengerBackCount.toString()

            Log.d("scratchhhh", itemList.toString())
        })

        binding.ivBeforeFront.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforeFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivBeforeBack.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforeBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivBeforeD.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforeDriveFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivBeforeDback.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforeDriveBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivBeforeP.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforePassengerFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivBeforePback.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.beforePassengerBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)

        }

        binding.ivAfterFront.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivAfterBack.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivAfterD.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterDriveFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivAfterDback.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterDriveBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivAfterP.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterPassengerFrontImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }

        binding.ivAfterPback.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("image", carImageViewModel.carImageLiveData.value?.get(0)?.afterPassengerBackImage)

            navController.navigate(R.id.action_picCheckAfterFragment_to_imageDetailFragment, bundle)
        }



        binding.btnReview.setOnClickListener {
            val bundle = Bundle()

            bundle.putSerializable("rent", rentInfo)

            navController.navigate(R.id.action_picCheckAfterFragment_to_reviewFragment, bundle)
        }


        super.initAfterBinding()
    }
}