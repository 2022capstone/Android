package com.android.r.base

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
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

    open fun encodeImageToBase64(bitmap: Bitmap, fileType : String) : String{

        Log.d("filetypee", fileType)
        val byteArrayOutputStream = ByteArrayOutputStream()
        if (fileType.equals("png"))
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        else if(fileType.equals("jpg") || fileType.equals("jpeg"))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}