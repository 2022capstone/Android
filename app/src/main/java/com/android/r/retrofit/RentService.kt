package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RentService {

    @GET("rentlist/rent-renter-id")
    fun getRentByRenterId(@Query("id")id: String) : Single<APIResponse<RentInfoResponse<List<Rent>>>>


    @GET("rentlist/rent-owner-id")
    fun getRentByOwnerId(@Query("id")id: String) : Single<APIResponse<RentInfoResponse<List<Rent>>>>

    @GET("carlist/car-rent-status") //대여 상태에 따른 차량 검색
    //id는 빌리는 사람 아이디(차주 아님), status는 현재 대여 상태
    fun getCarsByUserAndRentStatus(@Query("id")id: String, @Query("status")status: String) : Single<APIResponse<CarInfoResponse<List<Car>>>>

    @POST("rentlist/rent")
    suspend fun insertRentInfo(@Body request : RequestBody) : Response<ResponseBody>

    @PUT("rentlist/rent")
    suspend fun updateRentInfo(@Body request: RequestBody) : Response<ResponseBody>

    @DELETE("rentlist/rent")
    suspend fun deleteRentInfo(@Query("id")id: Int) : Response<ResponseBody>

}