package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RentService {

    @GET("rentlist/rent-renter-id")
    fun getRentByRenterId(@Query("id")id: String) : Single<APIResponse<RentInfoResponse<List<Rent>>>>


    @GET("rentlist/rent-owner-id")
    fun getRentByOwnerId(@Query("id")id: String) : Single<APIResponse<RentInfoResponse<List<Rent>>>>

}