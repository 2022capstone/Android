package com.android.r.model

data class APIResponse<T>(
    val resultMsg : String,
    val resultCode : String,
    val totalCount : Int,
    val list : List<T>
)
