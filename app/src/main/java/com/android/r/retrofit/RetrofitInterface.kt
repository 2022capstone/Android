package com.android.r.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    //https://www.--------.com/serch/photos/?query=""

    @GET("/search/photos")
    fun serchPhotos(@Query("query") searchTerm: String) : Call<JsonElement>

    @GET("/search/users")
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>

}