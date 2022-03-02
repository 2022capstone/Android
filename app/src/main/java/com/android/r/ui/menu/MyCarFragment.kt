package com.android.r.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentMyCarBinding
import com.android.r.ui.CarList


class MyCarFragment : BaseFragment<FragmentMyCarBinding>(R.layout.fragment_my_car) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyCarBinding.inflate(inflater, container, false)

        //대여요청현황
        val requestlentalList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22","반납대기"),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23","예약대기"),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24","예약승인"),
            CarList(R.drawable.car_front, "model4", "owner4", "2022.02.24~2022.02.25","예약대기")
        )

        binding.rvRequestRental.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRequestRental.setHasFixedSize(true)

        var adapter = RequestRentalAdapter(requestlentalList, this)
        binding.rvRequestRental.adapter = adapter
        adapter.setOnItemClickListener(object : RequestRentalAdapter.onItemClickListener{
            override fun onItemClick(position: Button) {
                if(position.text == "예약대기") {
                    navController.navigate(R.id.action_myCarFragment_to_profileCheckFragment)
                }//예약대기
                if(position.text == "예약승인"){
                    navController.navigate(R.id.action_myCarFragment_to_picCheckFragment)//예약승인
                }
                if(position.text == "반납대기"){
                    navController.navigate(R.id.action_myCarFragment_to_picCheckAfterFragment)//반납대기
                }
            }
        })

        //binding.rvRequestRental.adapter = RequestRentalAdapter(requestlentalList, this)




        //내차리스트
        val myCarList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22","대여가능"),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23","대여가능"),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24","대여불가능")
        )

        binding.rvMycarlist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMycarlist.setHasFixedSize(true)

        binding.rvMycarlist.adapter = MyCarListAdapter(myCarList, this)

        //차량등록버튼
        binding.btnCarRegistration.setOnClickListener {
            navController.navigate(R.id.action_myCarFragment_to_carRegisterFragment)
        }


        return binding.root
    }

}