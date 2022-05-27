package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.*
import com.android.r.repository.CarRepository
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

    fun insertCarInfo(car: CarInfo){
        val jsonObject = JSONObject()

        jsonObject.put("number", car.number)
        jsonObject.put("model", car.model)
        jsonObject.put("location", car.location)
        jsonObject.put("maxPeople", car.maxPeople)
        jsonObject.put("imageURL", car.imageURL)
        jsonObject.put("availableStatus", car.availableStatus)
        jsonObject.put("availableStartTime", car.availableStartTime)
        jsonObject.put("availableEndTime", car.availableEndTime)
        jsonObject.put("ownerId", car.ownerId)


        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = carRepository.insertCarInfo(requestBody)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        response.body()?.string()
                    )
                    Log.d("Insert rentInfo : ", prettyJson)

                }else{
                    Log.e("Retorfit_Error", response.code().toString())
                }

            }

        }
    }

    fun getCarsByLocation(location : String){
        addDisposable(
            carRepository.getCarInfoByLocation(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        if(resultMsg == "SUCCESS"){
                            _myCarLiveData.setValue(this.list.carInfo)
                            Log.d("dataa", this.list.carInfo.toString())
                        }
                        else{
                            Log.d("dataaa", this.list.carInfo.toString())
                        }
                    }
                },{
                    Log.d("errorrr", it.message.toString())
                    handleError(it)
                })
        )
    }

    fun updateCarInfo(car: CarInfo){

        val jsonObject = JSONObject()
        jsonObject.put("number", car.number)
        jsonObject.put("model", car.model)
        jsonObject.put("location", car.location)
        jsonObject.put("maxPeople", car.maxPeople)
        jsonObject.put("imageURL", car.imageURL)
        jsonObject.put("availableStartTime", car.availableStartTime)
        jsonObject.put("availableEndTime", car.availableEndTime)
        jsonObject.put("availableStatus", car.availableStatus)
        jsonObject.put("ownerId", car.ownerId)

        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = carRepository.updateCarInfo(requestBody)

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        response.body()?.string()
                    )
                    Log.d("Update Customer : ", prettyJson)

                }else{
                    Log.e("Retorfit_Error", response.code().toString())
                }

            }

        }
    }




    private fun handleError(exception: Throwable){
        val message = exception.message ?: ""
        _error.value = Event(message)
    }

}


