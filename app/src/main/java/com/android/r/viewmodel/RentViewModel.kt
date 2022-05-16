package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.*
import com.android.r.repository.CarRepository
import com.android.r.repository.RentRepository
import com.android.r.util.Event
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

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

    fun insertRentInfo(rent : RentInfo){

        val jsonObject = JSONObject()
        jsonObject.put("renterId", rent.renterId)
        jsonObject.put("startTime", rent.startTime)
        jsonObject.put("returnTime", rent.endTime)
        jsonObject.put("status", rent.status)
        jsonObject.put("grade", rent.grade)
        jsonObject.put("comment", rent.comment)
        jsonObject.put("carNum", rent.carNum)


        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = rentRepository.insertRentInfo(requestBody)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        response.body()?.string()
                    )
                }
            }
        }
    }

    fun updateRentInfo(rent: RentInfo){
        val jsonObject = JSONObject()
        jsonObject.put("rentId", rent.rentId)
        jsonObject.put("renterId", rent.renterId)
        jsonObject.put("startTime", rent.startTime)
        jsonObject.put("returnTime", rent.endTime)
        jsonObject.put("status", rent.status)
        jsonObject.put("grade", rent.grade)
        jsonObject.put("comment", rent.comment)
        jsonObject.put("carNum", rent.carNum)



        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = rentRepository.updateRentInfo(requestBody)
            Log.d("rentt", requestBody.toString())
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

    fun deleteRentInfo(id : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = rentRepository.deleteRentInfo(id)
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


