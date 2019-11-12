package com.core.base

import com.core.R
import com.core.dto.NetworkState
import com.core.utils.SingleLiveEvent
import com.google.gson.JsonSyntaxException
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException

abstract class BaseRepository {

    private val disposables = mutableListOf<Disposable>()

    val networkStatus : SingleLiveEvent<NetworkState> = SingleLiveEvent()

    open fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun disposeDisposables() {
        disposables.forEach { it.dispose() }
        disposables.clear()
    }

    open fun showProgressAction() {
         networkStatus.postValue(NetworkState.LOADING)
    }

    open fun hideProgressAction() {
        networkStatus.postValue(NetworkState.LOADED)
    }

    fun handleError(t: Throwable) {
        hideProgressAction()
        when (t) {
            is EOFException -> networkStatus.postValue(NetworkState.error(R.string.eofException))
            is IOException -> networkStatus.postValue(NetworkState.error(R.string.ioException))
            is HttpException -> when {
                t.code() == 401 -> {
                    networkStatus.postValue(NetworkState.error(R.string.authorization))
                }
                t.code() == 403 -> {
                    networkStatus.postValue(NetworkState.error(R.string.Forbidden))
                }
                else -> networkStatus.postValue(NetworkState.error(R.string.runtimeException))
            }
            is JsonSyntaxException -> networkStatus.postValue(NetworkState.error(R.string.jsonFormat))
            else -> networkStatus.postValue(NetworkState.error(R.string.undefine))
        }
    }
}