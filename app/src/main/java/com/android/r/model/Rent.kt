package com.android.r.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class Rent(
    val rentId : Int,
    val renterId : String, //빌리는 사람 아이디
    val carInfo : Car, // 빌리는 차량 정보
    val startTime : String, //예약한 시간 또는 대여 시작 시간
    @SerializedName("returnTime")
    val endTime: String,
    val status : String, //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
    val grade : Float, //대여 평점 -> 대여 끝나고 매겨짐
    val comment : String, //한줄평 -> 대여 끝나고 달림
    val detectDiv : String
) : Serializable