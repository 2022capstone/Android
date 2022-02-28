package com.android.r.ui

data class CarList(
    val image: Int,
    val model: String,
    val owner: String,
    val date: String,
    val state: String?) // 이미지, 차종, 차주, 대여가능날짜, 대여상태