package com.android.r.model

import java.io.Serializable

data class Customer(

    val id : String,

    val name : String,

    val phone : String,

    val address : String,

    val licenseNum : String,

    val gradeAvg : Float

) : Serializable
