package com.android.r.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.android.r.viewmodel.CarViewModel
import com.google.android.material.navigation.NavigationView
import org.koin.android.viewmodel.ext.android.viewModel


class Start2Fragment : BaseFragment<FragmentStart2Binding>(R.layout.fragment_start2), NavigationView.OnNavigationItemSelectedListener {

    val carViewModel : CarViewModel by viewModel()

    private lateinit var carListAdapter : CarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()

        binding.btnMenu.setOnClickListener{
            binding.layoutDrawer.openDrawer(GravityCompat.START)//START: left, END:right랑 같은 말
        }

        binding.navView.setNavigationItemSelectedListener(this)//네비게이션 메뉴 아이템에 클릭 속성 부여

        carListAdapter = CarListAdapter(ArrayList(), this.context!!)

        carViewModel.getMainList("nyh710")

        carViewModel.myCarLiveData.observe(this, { itemList ->
            carListAdapter.carList = itemList
        })

        binding.rvCarRentBefore.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCarRentBefore.setHasFixedSize(true)

        binding.rvCarRentBefore.adapter = carListAdapter
        carListAdapter.setOnItemClickListener(object : CarListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_start2Fragment_to_carSelectFragment)
            }
        })


        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            if(binding.layoutDrawer.isDrawerOpen(GravityCompat.START)){
                binding.layoutDrawer.closeDrawers()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
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




