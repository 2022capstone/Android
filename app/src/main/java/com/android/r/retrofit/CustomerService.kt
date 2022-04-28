package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {

    @GET("customerlist/customer") //아이디로 사용자 검색
    fun getCarInfoByLocation(@Query("id")id : String) : Single<APIResponse<CustomerResponse<List<Customer>>>>



}