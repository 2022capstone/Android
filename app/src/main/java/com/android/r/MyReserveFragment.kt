package com.android.r

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentMyReserveBinding


class MyReserveFragment : BaseFragment<FragmentMyReserveBinding>(R.layout.fragment_my_reserve) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentMyReserveBinding.inflate(inflater, container, false)

        //현재이용중인차량
        val currentcarList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22")
        )

        binding.rvCurrentCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCurrentCar.setHasFixedSize(true)

        binding.rvCurrentCar.adapter = CurrentCarAdapter(currentcarList, this)

        //과거이용했던차량
        val usedcarList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22"),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23"),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24"),
            CarList(R.drawable.car_front, "model4", "owner4", "2022.02.24~2022.02.25")
        )

        binding.rvUsedCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsedCar.setHasFixedSize(true)

        binding.rvUsedCar.adapter = CurrentCarAdapter(usedcarList, this)

        return binding.root
    }

}