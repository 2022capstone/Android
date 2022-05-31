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
import com.android.r.model.RentInfo
import com.android.r.ui.CarList
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class UsageDetailFragment : BaseFragment<FragmentUsageDetailBinding>(R.layout.fragment_usage_detail) {

    val rentViewModel : RentViewModel by viewModel()

    private lateinit var usedCarAdapter : UsedCarAdapter
    private lateinit var currentCarAdapter : CurrentCarAdapter

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

        //현재이용중인차량
        currentCarAdapter = CurrentCarAdapter(ArrayList(), requireContext())

        rentViewModel.getRentByRenterId("nyh710")

        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            currentCarAdapter.rentList = itemList
        })

        binding.rvCurrentCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCurrentCar.setHasFixedSize(true)

        binding.rvCurrentCar.adapter = currentCarAdapter
        currentCarAdapter.setOnItemClickListener(object : CurrentCarAdapter.onItemClickListener{
            override fun onItemClick(button: Button, position : Int){
                if(button.text == "예약대기"){
                    val bundle = Bundle()

                    bundle.putSerializable("rent", rentViewModel.rentInfoLiveData.value?.get(position))

                    Log.d("rentttt", rentViewModel.rentInfoLiveData.value?.get(position).toString())
                    navController.navigate(R.id.action_usageDetailFragment_to_takePicturesFragment, bundle)
                }

                if(button.text == "반납하기"){
                    val bundle = Bundle()

                    bundle.putSerializable("rent", rentViewModel.rentInfoLiveData.value?.get(position))

                    navController.navigate(R.id.action_usageDetailFragment_to_takePicturesFragment, bundle)
                }
            }
        })


        //과거이용했던차량
        usedCarAdapter = UsedCarAdapter(ArrayList(), requireContext())
        binding.rvUsedCar.adapter = usedCarAdapter

        binding.rvUsedCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsedCar.setHasFixedSize(true)

        binding.rvUsedCar.adapter = usedCarAdapter
        rentViewModel.getRentByRenterId("nyh710")
        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            usedCarAdapter.rentList = itemList
        })

        usedCarAdapter.setOnItemClickListener(object : UsedCarAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putSerializable("rent", rentViewModel.rentInfoLiveData.value?.get(position))
                bundle.putBoolean("btn", false)

                navController.navigate(R.id.action_usageDetailFragment_to_picCheckAfterFragment, bundle)
            }

        })


        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_usageDetailFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        super.initAfterBinding()

    }
}