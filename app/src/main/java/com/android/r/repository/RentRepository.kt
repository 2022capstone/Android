package com.android.r.repository

import com.android.r.model.APIResponse
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.retrofit.RentService
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class RentRepository(val rentService: RentService) {

    fun getRentByOwnerId(id : String) = rentService.getRentByOwnerId(id)

    fun getRentByRenterId(id : String) = rentService.getRentByRenterId(id)


}