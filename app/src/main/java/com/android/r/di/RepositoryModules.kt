package com.android.r.di

import com.android.r.repository.CarRepository
import com.android.r.repository.CustomerRepository
import com.android.r.repository.RentRepository
import com.android.r.retrofit.CarService
import com.android.r.retrofit.CustomerService
import com.android.r.retrofit.RentService
import org.koin.dsl.module.module

val repositoryModules = module {

    single{
        CarRepository(get<CarService>())
    }

    single {
        RentRepository(get<RentService>())
    }

    single {
        CustomerRepository(get<CustomerService>())
    }
}
