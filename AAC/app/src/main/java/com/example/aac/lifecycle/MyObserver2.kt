package com.example.aac.lifecycle

import android.util.Log
import androidx.lifecycle.*

class MyObserver2 : DefaultLifecycleObserver{
    companion object {
        val TAG = "MyObserver"
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.e(TAG, "connect...")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.e(TAG, "disConnect...")
    }
}