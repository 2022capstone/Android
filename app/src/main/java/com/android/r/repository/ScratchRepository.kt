package com.android.r.repository

import com.android.r.retrofit.CarService
import com.android.r.retrofit.ScratchService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ScratchRepository(private val scratchService: ScratchService) {
    fun getScratchInfo(id : Int) = scratchService.getScratchInfo(id)
}