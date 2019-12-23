package com.mobiquity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.core.base.BaseViewModel
import com.core.dto.CategoryModel
import com.core.dto.NetworkState
import com.core.dto.ProductModel
import com.core.utils.SingleLiveEvent
import com.mobiquity.data.ListResponse
import com.mobiquity.repositories.MainRepository


abstract class MainViewModel : BaseViewModel() {
    abstract fun requestList()
    abstract fun onProductSelected(product: ProductModel)
    abstract fun getDataResponse(): LiveData<List<CategoryModel>>
    abstract fun getProductSelected(): SingleLiveEvent<ProductModel>
}

public class MainViewModelImpl(
    private val repository: MainRepository
) : MainViewModel() {

    private val requestList  : SingleLiveEvent<Any>          = SingleLiveEvent()

    private val productSelect: SingleLiveEvent<ProductModel> = SingleLiveEvent()

    override fun getProductSelected(): SingleLiveEvent<ProductModel> = productSelect

    private val repo: LiveData<ListResponse> = Transformations.map(requestList) {
        repository.getListRequest()
    }

    override fun getDataResponse(): LiveData<List<CategoryModel>> =
        Transformations.switchMap(repo) {
            it.data
        }

    override fun getNetworkStatus(): LiveData<NetworkState> = Transformations.switchMap(repo) {
        it.networkState
    }

    override fun onProductSelected(product: ProductModel) {
        productSelect.postValue(product)
    }

    override fun requestList() {
        requestList.postValue(Any())
    }

    override fun onDestroy() {
        repository.disposeDisposables()
    }
}