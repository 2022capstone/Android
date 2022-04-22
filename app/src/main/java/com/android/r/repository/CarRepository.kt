package com.android.r.repository

import com.android.r.retrofit.CarService

class CarRepository(private val carService: CarService) {
    fun getCarInfoByLocation(location : String) = carService.getCarInfoByLocation(location)

    fun getCarInfoByUserLocation(id : String) = carService.getCarInfoByUserLocation(id)

    fun getCarsByUserAndRentStatus(id : String, status : String) = carService.getCarsByUserAndRentStatus(id, status)

    fun getMyCars(id: String) = carService.getMyCars(id)

}