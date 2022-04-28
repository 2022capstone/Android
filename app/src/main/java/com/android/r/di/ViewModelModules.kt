package com.android.r.di

import com.android.r.repository.CarRepository
import com.android.r.repository.CustomerRepository
import com.android.r.repository.RentRepository
import com.android.r.viewmodel.CarViewModel
import com.android.r.viewmodel.CustomerViewModel
import com.android.r.viewmodel.RentViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModules = module {

    viewModel{
        CarViewModel(get<CarRepository>())
    }

    viewModel{
        RentViewModel(get<RentRepository>())
    }

    viewModel{
        CustomerViewModel(get<CustomerRepository>())
    }
}