package com.android.r.di

import com.android.r.repository.*
import com.android.r.retrofit.*
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

    single{
        CarImageRepository(get<CarImageService>())
    }

    single{
        ScratchRepository(get<ScratchService>())
    }
}
