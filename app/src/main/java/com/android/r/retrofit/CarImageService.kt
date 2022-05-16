package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CarImageService {
    @GET("carImage/before/compare-images")
    fun getCarImageBeforeRentByRentId(@Query("id")id : Int) : Single<APIResponse<CarImageInfoResponse<List<CarImage>>>>

    @GET("carImage/after/compare-images")
    fun getCarImageAfterRentByRentId(@Query("id")id : Int) : Single<APIResponse<CarImageInfoResponse<List<CarImage>>>>

    @POST("carImage/before/compare-images")
    suspend fun insertCarImageBeforeRent(@Body data : RequestBody) : Response<ResponseBody>

}