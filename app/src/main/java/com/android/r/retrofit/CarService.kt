package com.android.r.retrofit

import com.android.r.model.APIResponse
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.model.Rent
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CarService {
    @GET("carlist/car-location") //검색창에 입력된 위치 기반으로 차 검색
    fun getCarInfoByLocation(@Query("location")location : String) : Single<APIResponse<CarInfoResponse<List<Car>>>> //사용자가 검색창에 입력한 위치 넣어줘야함

    @GET("carlist/car-userlocation") //처음 메인화면 로딩시 실행해서 사용자 위치 기반으로 차량 검색해줌
    fun getCarInfoByUserLocation(@Query("id")id : String) : Single<APIResponse<CarInfoResponse<List<Car>>>> //사용자의 위치 넣어줘야함

    @GET("carlist/car-rent-status") //대여 상태에 따른 차량 검색
    //id는 빌리는 사람 아이디(차주 아님), status는 현재 대여 상태
    fun getCarsByUserAndRentStatus(@Query("id")id: String, @Query("status")status: String) : Single<APIResponse<CarInfoResponse<List<Car>>>>

    @GET("carlist/my-car-list/{id}") //내 차량 정보 받아오기
    fun getMyCars(@Path("id")id: String) : Single<APIResponse<CarInfoResponse<List<Car>>>> //현재 로그인 되어 있는 사람 아이디

    @POST("carlist/car")
    suspend fun registMyCar(@Body data : RequestBody) : Response<ResponseBody>

}