package com.core.di



import com.core.api.MobiquityApi
import dagger.Component
import retrofit2.Retrofit


@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun getRetrofit(): Retrofit
    fun getMobiquityApi(): MobiquityApi

}