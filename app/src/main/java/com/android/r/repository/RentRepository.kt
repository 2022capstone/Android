package com.android.r.repository

import com.android.r.retrofit.RentService

class RentRepository(val rentService: RentService) {

    fun getRentByOwnerId(id : String) = rentService.getRentByOwnerId(id)

    fun getRentByRenterId(id : String) = rentService.getRentByRenterId(id)

}