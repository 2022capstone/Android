package com.android.r.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime


data class CarInfo(
    val number : String,

    val model : String,

    val location : String,

    val maxPeople : String,

    val imageURL : String,

    val availableStartTime : LocalDateTime,

    val availableEndTime : LocalDateTime,

    val availableStatus : String,

    val ownerId : String

)
