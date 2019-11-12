package com.mobiquity.repositories


import androidx.lifecycle.MutableLiveData
import com.core.BuildConfig
import com.core.api.MobiquityApi
import com.core.base.BaseRepository
import com.core.dto.CategoryModel
import com.mobiquity.data.ListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


abstract class MainRepository : BaseRepository() {
    abstract fun getListRequest(): ListResponse
}

class MainRepositoryImpl(private val services: MobiquityApi) : MainRepository() {

    override fun getListRequest(): ListResponse {
        showProgressAction()
        val data: MutableLiveData<List<CategoryModel>> = MutableLiveData()
        addDisposable(
            services.getDataApi(BuildConfig.baseUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.postValue(it)
                    hideProgressAction()
                }, {
                    handleError(it)
                })
        )
        return ListResponse(data = data, networkState = networkStatus)
    }

}