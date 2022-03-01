package com.android.r.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.google.android.material.navigation.NavigationView


class Start2Fragment : BaseFragment<FragmentStart2Binding>(R.layout.fragment_start2), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentStart2Binding.inflate(inflater, container, false)

        binding.btnMenu.setOnClickListener{
            binding.layoutDrawer.openDrawer(GravityCompat.START)//START: left, END:right랑 같은 말
        }

        binding.navView.setNavigationItemSelectedListener(this)//네비게이션 메뉴 아이템에 클릭 속성 부여

        val carList = arrayListOf(
            CarList(R.drawable.car_front, "model1", "owner1", "2022.02.21~2022.02.22",""),
            CarList(R.drawable.car_front, "model2", "owner2", "2022.02.22~2022.02.23",""),
            CarList(R.drawable.car_front, "model3", "owner3", "2022.02.23~2022.02.24",""),
            CarList(R.drawable.car_front, "model4", "owner4", "2022.02.24~2022.02.25",""),
            CarList(R.drawable.car_front, "model5", "owner5", "2022.02.25~2022.02.26",""),
            CarList(R.drawable.car_front, "model6", "owner6", "2022.02.26~2022.02.27",""),
            CarList(R.drawable.car_front, "model7", "owner7", "2022.02.27~2022.02.28",""),
        )

        binding.rvCarRentBefore.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCarRentBefore.setHasFixedSize(true)

        var adapter = CarListAdapter(carList, this)
        binding.rvCarRentBefore.adapter = adapter
        adapter.setOnItemClickListener(object : CarListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_start2Fragment_to_carSelectFragment)
            }
        })



        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            if(binding.layoutDrawer.isDrawerOpen(GravityCompat.START)){
                binding.layoutDrawer.closeDrawers()
            }
        }

        return binding.root
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //네비게이션 메뉴 아이템 클릭 시 수행
        when(item.itemId){
            R.id.myinformation -> navController.navigate(R.id.action_start2Fragment_to_profileFragment)
            R.id.myreservation -> navController.navigate(R.id.action_start2Fragment_to_myReservFragment)
            R.id.mycar -> navController.navigate(R.id.action_start2Fragment_to_myCarFragment)
            R.id.usagedetail -> navController.navigate(R.id.action_start2Fragment_to_usageDetailFragment)
            R.id.chat -> navController.navigate(R.id.action_start2Fragment_to_chatFragment)
        }

        return false
    }

}




