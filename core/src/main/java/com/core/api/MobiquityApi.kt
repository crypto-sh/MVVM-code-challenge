package com.core.api


import androidx.annotation.Keep
import com.core.dto.CategoryModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface MobiquityApi {

    @Keep
    @GET("")
    fun getDataApi(@Url url : String) : Single<List<CategoryModel>>
}