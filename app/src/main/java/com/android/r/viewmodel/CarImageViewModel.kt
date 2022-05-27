package com.android.r.viewmodel

import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.*
import com.android.r.repository.CarImageRepository
import com.android.r.repository.CarRepository
import com.android.r.util.Event
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

class CarImageViewModel(private val carImageRepository: CarImageRepository) : BaseViewModel(){
    private val _carImageLiveData : MutableLiveData<List<CarImage>> = MutableLiveData()
    val carImageLiveData : LiveData<List<CarImage>>
        get() = _carImageLiveData

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    fun getCarImageBeforeRentByRentId(id : Int) {
        addDisposable(
            carImageRepository.getCarImageBeforeRentByRentId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        if(resultMsg == "SUCCESS"){
                            _carImageLiveData.setValue(this.list.carImageInfo)
                            Log.d("imagee", this.list.carImageInfo.toString())
                        }else{
                            Log.d("imagee", this.error)
                        }
                    }
                },{
                    Log.d("imagee", it.message.toString())
                    handleError(it)
                })
        )
    }

    fun insertCarImageBeforeRent(carImage : CarImage){
        val jsonObject = JSONObject()

        jsonObject.put("rentId", carImage.rentId)
        jsonObject.put("beforeFrontImage", carImage.beforeFrontImage)
        jsonObject.put("beforeBackImage", carImage.beforeBackImage)
        jsonObject.put("beforeDriveFrontImage", carImage.beforeDriveFrontImage)
        jsonObject.put("beforeDriveBackImage", carImage.beforeDriveBackImage)
        jsonObject.put("beforePassengerFrontImage", carImage.beforePassengerFrontImage)
        jsonObject.put("beforePassengerBackImage", carImage.beforePassengerBackImage)
        jsonObject.put("afterFrontImage", carImage.afterFrontImage)
        jsonObject.put("afterBackImage", carImage.afterBackImage)
        jsonObject.put("afterDriveFrontImage", carImage.afterDriveFrontImage)
        jsonObject.put("afterDriveBackImage", carImage.afterDriveBackImage)
        jsonObject.put("afterPassengerFrontImage", carImage.afterPassengerFrontImage)
        jsonObject.put("afterPassengerBackImage", carImage.afterPassengerBackImage)


        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = carImageRepository.insertCarImageBeforeRent(requestBody)
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

    fun getCarImageAfterRentByRentId(id : Int) {
        addDisposable(
            carImageRepository.getCarImageAfterRentByRentId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        if(resultMsg == "SUCCESS"){
                            _carImageLiveData.setValue(this.list.carImageInfo)
                        }
                    }
                },{
                    handleError(it)
                })
        )
    }

    fun insertCarImageAfterRent(carImage : CarImage){
        val jsonObject = JSONObject()

        jsonObject.put("rentId", carImage.rentId)
        jsonObject.put("beforeFrontImage", carImage.beforeFrontImage)
        jsonObject.put("beforeBackImage", carImage.beforeBackImage)
        jsonObject.put("beforeDriveFrontImage", carImage.beforeDriveFrontImage)
        jsonObject.put("beforeDriveBackImage", carImage.beforeDriveBackImage)
        jsonObject.put("beforePassengerFrontImage", carImage.beforePassengerFrontImage)
        jsonObject.put("beforePassengerBackImage", carImage.beforePassengerBackImage)
        jsonObject.put("afterFrontImage", carImage.afterFrontImage)
        jsonObject.put("afterBackImage", carImage.afterBackImage)
        jsonObject.put("afterDriveFrontImage", carImage.afterDriveFrontImage)
        jsonObject.put("afterDriveBackImage", carImage.afterDriveBackImage)
        jsonObject.put("afterPassengerFrontImage", carImage.afterPassengerFrontImage)
        jsonObject.put("afterPassengerBackImage", carImage.afterPassengerBackImage)


        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = carImageRepository.insertCarImageAfterRent(requestBody)
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



    private fun handleError(exception: Throwable){
        val message = exception.message ?: ""
        _error.value = Event(message)
    }

}


