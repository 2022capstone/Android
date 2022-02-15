package com.android.r

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.google.android.material.navigation.NavigationView


class Start2Fragment : BaseFragment<FragmentStart2Binding>(R.layout.fragment_start2), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carList = arrayListOf(//model, owner, date
            CarList(R.drawable.car_front, "모델", "id", "2022.02.15"),
            CarList(R.drawable.car_front, "모델2", "id2", "2022.02.15"),
            CarList(R.drawable.car_front, "모델3", "id3", "2022.02.15")
        )

        /*binding.rvCarRentBefore.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvCarRentBefore.setHasFixedSize(true)

        binding.rvCarRentBefore.adapter = CarListAdapter(carList)*/
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

        return binding.root
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //네비게이션 메뉴 아이템 클릭 시 수행
        when(item.itemId){
            R.id.myreservation -> navController.navigate(R.id.action_global_myReservFragment)
            R.id.mycar -> navController.navigate(R.id.action_start2Fragment_to_myCarFragment)
            R.id.usagedetail -> navController.navigate(R.id.action_start2Fragment_to_usageDetailFragment)
            R.id.chat -> navController.navigate(R.id.action_start2Fragment_to_chatFragment)
        }
        //binding.layoutDrawer.closeDrawers() //네이게이션 뷰 닫기
        return false
    }



}


