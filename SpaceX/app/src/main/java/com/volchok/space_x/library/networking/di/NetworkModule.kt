package com.volchok.space_x.library.networking.di

import com.volchok.space_x.library.networking.device.AndroidNetworkController
import com.volchok.space_x.library.networking.domain.NetworkController
import com.volchok.space_x.library.networking.domain.ObserveConnectionUseCase
import com.volchok.space_x.library.networking.system.NetworkDelegate
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::AndroidNetworkController) bind NetworkController::class
    factoryOf(::NetworkDelegate)
    factoryOf(::ObserveConnectionUseCase)
}