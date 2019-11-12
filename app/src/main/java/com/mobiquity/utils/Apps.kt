package com.mobiquity.utils

import android.app.Application

class Apps : Application() {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun secretKey() :String
}