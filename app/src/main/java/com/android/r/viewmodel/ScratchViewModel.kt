package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.*
import com.android.r.repository.CarRepository
import com.android.r.repository.ScratchRepository
import com.android.r.util.Event
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

class ScratchViewModel(private val scratchRepository: ScratchRepository) : BaseViewModel(){
    private val _scratchLiveData : MutableLiveData<Scratch> = MutableLiveData()
    val scratchLiveData : LiveData<Scratch>
        get() = _scratchLiveData

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    fun getScratchInfo(id : Int){
        addDisposable(
            scratchRepository.getScratchInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        if(resultMsg == "SUCCESS"){
                            _scratchLiveData.setValue(this.list)
                        }
                    }
                },{
                    Log.d("scratcherror", it.message.toString())
                    handleError(it)
                })
        )
    }

    private fun handleError(exception: Throwable){
        val message = exception.message ?: ""
        _error.value = Event(message)
    }

}


