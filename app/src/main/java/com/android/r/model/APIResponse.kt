package com.android.r.model

data class APIResponse<T>(
    val resultMsg : String,
    val totalCount : Int,
    val list : T,
    val error : String
)
