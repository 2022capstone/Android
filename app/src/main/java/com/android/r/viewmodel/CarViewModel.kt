package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.model.Rent
import com.android.r.repository.CarRepository
import com.android.r.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarViewModel(private val carRepository: CarRepository) : BaseViewModel(){
    private val _myCarLiveData : MutableLiveData<List<Car>> = MutableLiveData()
    val myCarLiveData : LiveData<List<Car>>
        get() = _myCarLiveData

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    fun getMyCarList(id : String){
        Log.d("Carr", id)
        addDisposable(
            carRepository.getMyCars(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        Log.d("Carr", "test")
                        if(resultMsg == "SUCCESS"){
                            Log.d("Carr", this.list.carInfo.toString())
                            _myCarLiveData.setValue(this.list.carInfo)
                            Log.d("Carr", _myCarLiveData.value.toString())
                        }
                    }
                },{
                    Log.d("Car", it.message.toString())
                    handleError(it)
                })
        )
    }

    fun getMainList(id: String){
        addDisposable(
            carRepository.getCarInfoByUserLocation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        Log.d("Carr", "test")
                        if(resultMsg == "SUCCESS"){
                            _myCarLiveData.setValue(this.list.carInfo)
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


