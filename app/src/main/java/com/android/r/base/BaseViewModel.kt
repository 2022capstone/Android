package com.android.r.base

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.net.URLEncoder

abstract class BaseViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    open fun encodeImageToBase64(bitmap: Bitmap) : String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.URL_SAFE)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}