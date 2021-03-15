package com.example.aac.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : LifecycleObserver {
    companion object {
        val TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun connect() {
        Log.e(TAG, "connect...")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disConnect() {
        Log.e(TAG, "disConnect...")
    }
}