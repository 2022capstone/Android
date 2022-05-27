package com.android.r.ui.menu

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentMyCarBinding
import com.android.r.model.Car
import com.android.r.model.CarInfo
import com.android.r.util.EventObserver
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.CustomerViewModel
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class MyCarFragment : BaseFragment<FragmentMyCarBinding>(R.layout.fragment_my_car) {

    val carViewModel: CarViewModel by viewModel()
    val rentViewModel: RentViewModel by viewModel()
    val customerViewModel : CustomerViewModel by viewModel()


    private lateinit var myCarAdapter: MyCarListAdapter
    private lateinit var requestRentalAdapter: RequestRentalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        //차량등록버튼
        binding.btnCarRegistration.setOnClickListener {
            navController.navigate(R.id.action_myCarFragment_to_carRegisterFragment)
        }

        //뒤로가기
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            navController.navigate(R.id.action_myCarFragment_to_start2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        //대여요청현황황
        requestRentalAdapter = RequestRentalAdapter(ArrayList(), this.context!!)

        rentViewModel.getRentByOwnerId("nyh710")

        binding.rvRequestRental.adapter = requestRentalAdapter

        rentViewModel.rentInfoLiveData.observe(this, { itemList ->
            requestRentalAdapter.rentList = itemList
        })

        binding.rvRequestRental.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRequestRental.setHasFixedSize(true)



        requestRentalAdapter.setOnItemClickListener(object : RequestRentalAdapter.onItemClickListener {
            override fun onItemClick(button: Button, position: Int) {

                if (button.text == "예약대기") {
                    val bundle = Bundle()

                    bundle.putSerializable("rent", rentViewModel.rentInfoLiveData.value?.get(position))

                    navController.navigate(
                        R.id.action_myCarFragment_to_profileCheckFragment,
                        bundle
                    )//예약대기
                }
                if (button.text == "예약승인") {
                    val bundle = Bundle()

                    bundle.putSerializable(
                        "rent",
                        rentViewModel.rentInfoLiveData.value?.get(position)
                    )

                    navController.navigate(
                        R.id.action_myCarFragment_to_picCheckFragment,
                        bundle
                    )//예약승인
                }
                if (button.text == "반납승인") {
                    val bundle = Bundle()

                    bundle.putSerializable(
                        "rent",
                        rentViewModel.rentInfoLiveData.value?.get(position)
                    )
                    bundle.putBoolean("btn", true)

                    navController.navigate(
                        R.id.action_myCarFragment_to_picCheckAfterFragment,
                        bundle
                    )//반납승인
                }
            }
        })

        //binding.rvRequestRental.adapter = RequestRentalAdapter(requestlentalList, this)
        myCarAdapter = MyCarListAdapter(ArrayList(), this.context!!)
        binding.rvMycarlist.adapter = myCarAdapter


        //내차리스트
        carViewModel.getMyCarList("nyh710")
        carViewModel.myCarLiveData.observe(this, { itemList ->
            Log.d("itmeListt", itemList.toString())
            myCarAdapter.carList = itemList
        })

        carViewModel.error.observe(this, EventObserver {
            Log.d("Carr", carViewModel.error.value.toString())
        })
        binding.rvMycarlist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMycarlist.setHasFixedSize(true)

        myCarAdapter.setOnItemClickListener(object : MyCarListAdapter.onItemClickListener {
            override fun onItemClick(button: Button, position: Int) {

                if (button.text == "대여가능") {
                    button.text = "대여불가능"
                    carViewModel.updateCarInfo(
                        CarInfo(
                            carViewModel.myCarLiveData.value?.get(position)?.carNumber!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carModel!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carLocation!!,
                            carViewModel.myCarLiveData.value?.get(position)?.seater!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carImage!!,
                            LocalDateTime.parse(carViewModel.myCarLiveData.value?.get(position)?.availableStartTime!!, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            LocalDateTime.parse(carViewModel.myCarLiveData.value?.get(position)?.availableEndTime!!, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            "n",
                            "nyh710"
                        )
                    )
                } else if (button.text == "대여불가능") {
                    button.text = "대여가능"
                    carViewModel.updateCarInfo(
                        CarInfo(
                            carViewModel.myCarLiveData.value?.get(position)?.carNumber!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carModel!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carLocation!!,
                            carViewModel.myCarLiveData.value?.get(position)?.seater!!,
                            carViewModel.myCarLiveData.value?.get(position)?.carImage!!,
                            LocalDateTime.parse(carViewModel.myCarLiveData.value?.get(position)?.availableStartTime!!, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            LocalDateTime.parse(carViewModel.myCarLiveData.value?.get(position)?.availableEndTime!!, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            "y",
                            "nyh710"
                        )
                    )
                }
            }
        })

    }
}