package com.android.r.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.r.base.BaseViewModel
import com.android.r.model.Car
import com.android.r.model.Customer
import com.android.r.repository.CustomerRepository
import com.android.r.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CustomerViewModel(private val customerRepository: CustomerRepository) : BaseViewModel(){
    private val _customerLiveData : MutableLiveData<List<Customer>> = MutableLiveData()
    val customerLiveData : LiveData<List<Customer>>
        get() = _customerLiveData

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



    private fun handleError(exception: Throwable){
        val message = exception.message ?: ""
        _error.value = Event(message)
    }

}


