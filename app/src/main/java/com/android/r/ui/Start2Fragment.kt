package com.android.r.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.android.r.databinding.NavHeaderMainBinding
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.CustomerViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class Start2Fragment : BaseFragment<FragmentStart2Binding>(R.layout.fragment_start2), NavigationView.OnNavigationItemSelectedListener {

    val carViewModel : CarViewModel by viewModel()
    val customerViewModel : CustomerViewModel by viewModel()
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
                val bundle = Bundle()
                bundle.putString("model", "${carViewModel.myCarLiveData.value?.get(position)?.carModel}")
                bundle.putString("image", "${carViewModel.myCarLiveData.value?.get(position)?.carImage}")
                bundle.putString("seater", "${carViewModel.myCarLiveData.value?.get(position)?.seater}")
                bundle.putString("number", "${carViewModel.myCarLiveData.value?.get(position)?.carNumber}")

                navController.navigate(R.id.action_start2Fragment_to_carSelectFragment, bundle)

            }
        })


        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isEmpty()){
                    carListAdapter.carList = ArrayList()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(s?.isEmpty() == true){
                    carViewModel.getMainList("nyh710")

                    carViewModel.myCarLiveData.observe(this@Start2Fragment, { itemList ->
                        Log.d("keyyy", itemList.toString())
                        carListAdapter.carList = itemList
                    })

                }else{
                    carViewModel.getCarsByLocation(s.toString())
                    carViewModel.myCarLiveData.observe(this@Start2Fragment, { list ->
                        Log.d("keyy", list.toString())
                        carListAdapter.carList = list
                    })
                }
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
        }

        return false
    }

}




