package com.mobiquity.viewModel

import androidx.lifecycle.LiveData
import com.core.base.BaseViewModel
import com.core.dto.NetworkState
import com.core.utils.SingleLiveEvent

abstract class DetailViewModel : BaseViewModel()


class DetailViewModelImpl : DetailViewModel() {

    override fun getNetworkStatus(): LiveData<NetworkState> = SingleLiveEvent()

    override fun onDestroy() {

    }
}
