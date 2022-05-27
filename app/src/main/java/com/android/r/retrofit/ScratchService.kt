package com.android.r.retrofit

import com.android.r.model.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ScratchService {
    @GET("scratch") //검색창에 입력된 위치 기반으로 차 검색
    fun getScratchInfo(@Query("id")id : Int) :  Single<APIResponse<Scratch>>

}