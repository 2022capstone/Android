package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CustomerService {

    @GET("customerlist/customer") //아이디로 사용자 검색
    fun getCarInfoByLocation(@Query("id")id : String) : Single<APIResponse<CustomerResponse<List<Customer>>>>

    @PUT("customerlist/customer")
    suspend fun updateCustomer(@Body request : RequestBody) : Response<ResponseBody>

}