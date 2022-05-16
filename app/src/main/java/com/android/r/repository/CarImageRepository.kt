package com.android.r.repository

import com.android.r.retrofit.CarImageService
import com.android.r.retrofit.CarService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CarImageRepository(private val carImageService: CarImageService) {
    fun getCarImageBeforeRentByRentId(id : Int) = carImageService.getCarImageBeforeRentByRentId(id)

    fun getCarImageAfterRentByRentId(id : Int) = carImageService.getCarImageAfterRentByRentId(id)

    suspend fun insertCarImageBeforeRent(data : RequestBody) = carImageService.insertCarImageBeforeRent(data)
}