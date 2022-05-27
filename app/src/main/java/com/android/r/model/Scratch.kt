package com.android.r.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class Scratch(
    val id : Int,

    val beforeFrontCount : Int,

    val beforeBackCount : Int,

    val beforeDriveFrontCount : Int,

    val beforeDriveBackCount : Int,

    val beforePassengerFrontCount : Int,

    val beforePassengerBackCount : Int,

    val afterFrontCount : Int,

    val afterBackCount : Int,

    val afterDriveFrontCount : Int,

    val afterDriveBackCount : Int,

    val afterPassengerFrontCount : Int,

    val afterPassengerBackCount : Int,

) : Serializable
