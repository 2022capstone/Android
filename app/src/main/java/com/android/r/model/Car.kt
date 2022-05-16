package com.android.r.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class Car(
    @SerializedName("number")
    val carNumber : String,

    @SerializedName("model")
    val carModel : String,

    @SerializedName("location")
    val carLocation : String,

    @SerializedName("maxPeople")
    val seater : String,

    @SerializedName("imageURL")
    val carImage : String,

    val availableStartTime : String,

    val availableEndTime : String,

    val availableStatus : String,

    val ownerId : String
) : Serializable
