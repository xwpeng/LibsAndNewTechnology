package com.example.aac.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService

class MainService : LifecycleService() {
    companion object{
        val TAG = "Mainservice"
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "MainService onCreate ")
        lifecycle.addObserver(ObServer())
    }

    class ObServer : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            Log.e(TAG, "onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            Log.e(TAG, "onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.e(TAG, "onResume")
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            Log.e(TAG, "onPause")
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            Log.e(TAG, "onStop")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            Log.e(TAG, "onDestroy")
        }
    }

}