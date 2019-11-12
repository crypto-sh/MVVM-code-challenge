package com.core.dto

import com.core.R


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

public data class NetworkState(
    val status  : Status,
    val event   : Int       = R.string.Forbidden,
    val msg     : String?   = null
) {

    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        public fun error(event: Int, msg: String? = null) = NetworkState(status = Status.FAILED, event = event, msg = msg)
    }
}