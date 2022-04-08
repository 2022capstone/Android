package com.android.r.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Rent(
    val renterId : String, //빌리는 사람 아이디
    val carInfo : Car, // 빌리는 차량 정보
    val startTime : LocalDateTime, //예약한 시간 또는 대여 시작 시간
    val endTime: LocalDateTime,
    val status : String,
    val grade : Float, //대여 평점 -> 대여 끝나고 매겨짐
    val comment : String //한줄평 -> 대여 끝나고 달림
)