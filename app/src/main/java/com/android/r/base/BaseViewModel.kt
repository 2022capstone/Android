package com.android.r.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Exception

abstract class BaseViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}