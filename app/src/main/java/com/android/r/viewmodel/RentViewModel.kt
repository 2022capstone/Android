package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.model.Rent
import com.android.r.repository.CarRepository
import com.android.r.repository.RentRepository
import com.android.r.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RentViewModel(private val rentRepository: RentRepository) : BaseViewModel(){
    private val _rentInfoLiveData : MutableLiveData<List<Rent>> = MutableLiveData()
    val rentInfoLiveData : LiveData<List<Rent>>
        get() = _rentInfoLiveData

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    fun getRentByOwnerId(id : String){
        addDisposable(
            rentRepository.getRentByOwnerId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{

                        if(resultMsg == "SUCCESS"){
                            _rentInfoLiveData.setValue(this.list.rentInfo)
                        }
                    }
                },{
                    Log.d("Car", it.message.toString())
                    handleError(it)
                })
        )
    }

    fun getRentByRenterId(id : String){
        Log.d("Carr", id)
        addDisposable(
            rentRepository.getRentByRenterId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        Log.d("Carr", "test")
                        if(resultMsg == "SUCCESS"){
                            _rentInfoLiveData.setValue(this.list.rentInfo)
                        }
                    }
                },{
                    Log.d("Car", it.message.toString())
                    handleError(it)
                })
        )
    }





    private fun handleError(exception: Throwable){
        val message = exception.message ?: ""
        _error.value = Event(message)
    }

}


