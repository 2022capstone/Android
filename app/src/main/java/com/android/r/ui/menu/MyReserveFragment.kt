package com.android.r.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentMyReserveBinding
import com.android.r.ui.CarList


class MyReserveFragment : BaseFragment<FragmentMyReserveBinding>(R.layout.fragment_my_reserve) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentMyReserveBinding.inflate(inflater, container, false)

        //예약내역
        val reserveList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22",""),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23",""),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24",""),
            CarList(R.drawable.car_front, "model4", "owner4", "2022.02.24~2022.02.25",""),
            CarList(R.drawable.car_front, "model5", "owner5", "2022.02.25~2022.02.26","")
        )

        binding.rvReserve.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvReserve.setHasFixedSize(true)

        binding.rvReserve.adapter = ReservationAdapter(reserveList, this)

        return binding.root
    }

}