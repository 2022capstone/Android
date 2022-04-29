package com.android.r.repository

import com.android.r.retrofit.CarService
import com.android.r.retrofit.CustomerService
import okhttp3.RequestBody

class CustomerRepository(private val customerService: CustomerService) {
    fun getCustomerById(id : String) = customerService.getCarInfoByLocation(id)

    suspend fun updateCustomer(request : RequestBody) = customerService.updateCustomer(request)
}