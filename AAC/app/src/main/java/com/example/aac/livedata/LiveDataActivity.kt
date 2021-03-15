package com.example.aac.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.example.aac.R

class LiveDataActivity : AppCompatActivity() {
    companion object {
        val TAG = "LiveDataActivity"
    }

    private lateinit var mData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        mData = MutableLiveData()
        mData.observe(this,
            Observer<String> {
                Log.e(TAG, "onChanged: ${it}");
            })
        Log.e(TAG, "onCreate")
        mData.value = "oncreate"
        mData.observeForever {
            Log.e(TAG, "observeForever onChanged: ${it}");
        }
        Transformations.map()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
//        mData.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
        mData.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
        mData.value = "onPause"
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
        mData.value = "onStop"
    }


    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart")
        mData.value = "onRestart"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
        mData.value = "onDestroy"
    }

}