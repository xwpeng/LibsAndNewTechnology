package com.example.aac.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class TestSelfLifecycleOwner : LifecycleOwner {
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }

    init {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun onCreate(){
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun onDestroy(){
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}