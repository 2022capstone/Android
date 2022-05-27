package com.android.r.di

import com.android.r.repository.*
import com.android.r.viewmodel.*
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

    viewModel{
        CarImageViewModel(get<CarImageRepository>())
    }

    viewModel{
        ScratchViewModel(get<ScratchRepository>())
    }
}