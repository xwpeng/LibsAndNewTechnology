package com.example.aac.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface BasePresenter : LifecycleObserver {
    //网络请求dispose
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe()
}