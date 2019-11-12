package com.mobiquity.data

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import com.core.dto.CategoryModel
import com.core.dto.NetworkState


@Keep
data class ListResponse(
    @Keep val networkState  : LiveData<NetworkState>,
    @Keep val data          : LiveData<List<CategoryModel>>
)