package com.android.r.repository

import com.android.r.retrofit.CarService
import com.android.r.retrofit.CustomerService

class CustomerRepository(private val customerService: CustomerService) {
    fun getCustomerById(id : String) = customerService.getCarInfoByLocation(id)
}