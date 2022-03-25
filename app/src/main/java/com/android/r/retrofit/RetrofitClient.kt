package com.android.r.retrofit

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null

    //레트로핏 클라리언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d(TAG, "getClient")

        if (retrofitClient == null) {
            //레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitClient
    }
}


