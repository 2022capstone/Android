package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.Car
import com.android.r.model.Customer
import com.android.r.repository.CustomerRepository
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

class CustomerViewModel(private val customerRepository: CustomerRepository) : BaseViewModel(){
    private val _customerLiveData : MutableLiveData<List<Customer>> = MutableLiveData()
    val customerLiveData : LiveData<List<Customer>>
        get() = _customerLiveData

    private val userId = "nyh710"
    private val userName = "남윤형"

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    fun getCustomerById(id : String){
        Log.d("Carr", id)
        addDisposable(
            customerRepository.getCustomerById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run{
                        if(resultMsg == "SUCCESS"){
                            _customerLiveData.setValue(this.list.customerInfo)
                            Log.d("Customerr", _customerLiveData.value.toString())
                        }
                    }
                },{
                    Log.d("Customerr", it.message.toString())
                    handleError(it)
                })
        )
    }

    fun updateCustomer(customer : Customer){

        val jsonObject = JSONObject()
        jsonObject.put("id", customer.id)
        jsonObject.put("name", customer.name)
        jsonObject.put("phone", customer.phone)
        jsonObject.put("address", customer.address)
        jsonObject.put("licenseNum", customer.licenseNum)
        jsonObject.put("gradeAvg", customer.gradeAvg)

        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = customerRepository.updateCustomer(requestBody)

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


