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
import com.android.r.databinding.FragmentMyCarBinding
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.ui.CarList
import com.android.r.util.EventObserver
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MyCarFragment : BaseFragment<FragmentMyCarBinding>(R.layout.fragment_my_car) {

    val carViewModel : CarViewModel by viewModel()
    val rentViewModel : RentViewModel by viewModel()

    private lateinit var myCarAdapter : MyCarListAdapter
    private lateinit var requestRentalAdapter : RequestRentalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        //대여 요청 현황
        requestRentalAdapter = RequestRentalAdapter(ArrayList(), this.context!!)

        rentViewModel.getRentByOwnerId("nyh710")

        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            requestRentalAdapter.rentList = itemList
            Log.d("rentt", itemList.toString())
        })

        binding.rvRequestRental.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRequestRental.setHasFixedSize(true)


        binding.rvRequestRental.adapter = requestRentalAdapter
        requestRentalAdapter.setOnItemClickListener(object : RequestRentalAdapter.onItemClickListener{
            override fun onItemClick(position: Button) {
                if(position.text == "예약대기") {
                    navController.navigate(R.id.action_myCarFragment_to_profileCheckFragment)//예약대기
                }
                if(position.text == "예약승인"){
                    navController.navigate(R.id.action_myCarFragment_to_picCheckFragment)//예약승인
                }
                if(position.text == "반납대기"){
                    navController.navigate(R.id.action_myCarFragment_to_picCheckAfterFragment)//반납대기
                }
            }
        })




        //binding.rvRequestRental.adapter = RequestRentalAdapter(requestlentalList, this)
        myCarAdapter = MyCarListAdapter(ArrayList(), this.context!!)
        binding.rvMycarlist.adapter = myCarAdapter
        //내차리스트
        carViewModel.getMyCarList("nyh710")
        carViewModel.myCarLiveData.observe(this, { itemList ->
            myCarAdapter.carList = itemList
        })

        carViewModel.error.observe(this, EventObserver{
            Log.d("Carr", carViewModel.error.value.toString())
        })
        binding.rvMycarlist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMycarlist.setHasFixedSize(true)



        //차량등록버튼
        binding.btnCarRegistration.setOnClickListener {
            navController.navigate(R.id.action_myCarFragment_to_carRegisterFragment)
        }




        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_myCarFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}