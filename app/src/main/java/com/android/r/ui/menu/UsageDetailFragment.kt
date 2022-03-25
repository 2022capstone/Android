package com.android.r.ui.menu

import android.os.Bundle
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

class UsageDetailFragment : BaseFragment<FragmentUsageDetailBinding>(R.layout.fragment_usage_detail) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        //현재이용중인차량
        val currentcarList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22", "대여준비")
        )

        binding.rvCurrentCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCurrentCar.setHasFixedSize(true)

        var currenrcaradapter = CurrentCarAdapter(currentcarList, this)
        binding.rvCurrentCar.adapter = currenrcaradapter
        currenrcaradapter.setOnItemClickListener(object : CurrentCarAdapter.onItemClickListener{
            override fun onItemClick(position: Button){
                if(position.text == "대여준비"){
                    navController.navigate(R.id.action_usageDetailFragment_to_takePicturesFragment)
                }
            }
        })


        //과거이용했던차량
        val usedcarList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22","반납완료"),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23","반납완료"),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24","반납완료"),
            CarList(R.drawable.car_front, "model4", "owner4", "2022.02.24~2022.02.25","반납완료")
        )

        binding.rvUsedCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsedCar.setHasFixedSize(true)

        //binding.rvUsedCar.adapter = UsedCarAdapter(usedcarList, this)
        var usedcaradapter = UsedCarAdapter(usedcarList, this)
        binding.rvUsedCar.adapter = usedcaradapter
        usedcaradapter.setOnItemClickListener(object : UsedCarAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_usageDetailFragment_to_carDetailFragment)
            }

        })


        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_usageDetailFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


}