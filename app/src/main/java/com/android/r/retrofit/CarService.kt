package com.android.r.retrofit

import com.android.r.model.APIResponse
import com.android.r.model.Car
import io.reactivex.Single
import retrofit2.http.GET

interface CarService {
    @GET("carlist/car-loaction?location={location}")
    fun getCarInfoByLocation() : Single<APIResponse<List<Car>>>

}