package com.android.r.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentMyReserveBinding
import com.android.r.ui.CarList
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MyReserveFragment : BaseFragment<FragmentMyReserveBinding>(R.layout.fragment_my_reserve) {

    val rentViewModel : RentViewModel by viewModel()

    private lateinit var reservationAdapter : ReservationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {

        //예약내역
        reservationAdapter = ReservationAdapter(ArrayList(), requireContext())

        rentViewModel.getRentByRenterId("nyh710")

        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            reservationAdapter.rentList = itemList
        })

        binding.rvReserve.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvReserve.setHasFixedSize(true)

        //binding.rvReserve.adapter = ReservationAdapter(reserveList, this)
        binding.rvReserve.adapter = reservationAdapter
        reservationAdapter.setOnItemClickListener(object : ReservationAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("model", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carModel}")
                bundle.putString("image", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carImage}")
                bundle.putString("seater", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.seater}")
                bundle.putString("number", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carNumber}")
                bundle.putString("location", "${rentViewModel.rentInfoLiveData.value?.get(position)?.carInfo?.carLocation}")
                bundle.putString("starttime", "${rentViewModel.rentInfoLiveData.value?.get(position)?.startTime}")
                bundle.putString("endtime", "${rentViewModel.rentInfoLiveData.value?.get(position)?.endTime}")

                navController.navigate(R.id.action_myReservFragment_to_carDetailFragment, bundle)
            }

        })

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_myReservFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        super.initAfterBinding()
    }
}