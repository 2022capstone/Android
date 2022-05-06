package com.android.r.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentUsageDetailBinding
import com.android.r.ui.CarList
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UsageDetailFragment : BaseFragment<FragmentUsageDetailBinding>(R.layout.fragment_usage_detail) {

    val rentViewModel : RentViewModel by viewModel()

    private lateinit var usedCarAdapter : UsedCarAdapter
    private lateinit var currentCarAdapter : CurrentCarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        //현재이용중인차량
        currentCarAdapter = CurrentCarAdapter(ArrayList(), this.context!!)

        rentViewModel.getRentByRenterId("nyh710")

        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            currentCarAdapter.rentList = itemList
            Log.d("rentt", itemList.toString())
        })

        binding.rvCurrentCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCurrentCar.setHasFixedSize(true)

        binding.rvCurrentCar.adapter = currentCarAdapter
        currentCarAdapter.setOnItemClickListener(object : CurrentCarAdapter.onItemClickListener{
            override fun onItemClick(position: Button){
                if(position.text == "대여준비"){
                    navController.navigate(R.id.action_usageDetailFragment_to_takePicturesFragment)
                }
            }
        })



        //과거이용했던차량
        usedCarAdapter = UsedCarAdapter(ArrayList(), this.context!!)
        binding.rvUsedCar.adapter = usedCarAdapter

        binding.rvUsedCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsedCar.setHasFixedSize(true)

        binding.rvUsedCar.adapter = usedCarAdapter
        rentViewModel.getRentByRenterId("nyh710")
        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            usedCarAdapter.rentList = itemList
            Log.d("Rentt", itemList.toString())
        })

        usedCarAdapter.setOnItemClickListener(object : UsedCarAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("model", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carModel}")
                bundle.putString("image", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carImage}")
                bundle.putString("seater", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.seater}")
                bundle.putString("number", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carNumber}")
                bundle.putString("location", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carLocation}")
                bundle.putString("starttime", "${rentViewModel.rentInfoLiveData.value?.get(position)?.startTime}")
                bundle.putString("endtime", "${rentViewModel.rentInfoLiveData.value?.get(position)?.endTime}")

                navController.navigate(R.id.action_usageDetailFragment_to_carDetailFragment, bundle)
            }

        })



        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_usageDetailFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


}