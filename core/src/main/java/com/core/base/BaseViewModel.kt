package com.core.base



import androidx.lifecycle.*

import com.core.dto.NetworkState


abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    abstract fun getNetworkStatus() : LiveData<NetworkState>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreated(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    abstract fun onDestroy()
}