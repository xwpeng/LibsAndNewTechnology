package com.example.aac.livedata

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
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
//        mData.observeForever {
//            Log.e(TAG, "observeForever onChanged: ${it}");
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
//        mData.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
//        mData.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
//        mData.value = "onPause"
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
//        mData.value = "onStop"
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart")
//        mData.value = "onRestart"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
//        mData.value = "onDestroy"
    }

    fun map() {
        val intData = MutableLiveData<Int>()
        Transformations.map(intData) {
            "toString".plus(it)
        }.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        intData.value = 1
    }

    fun strategy() {
        val oddData = MutableLiveData<Int>()
        val evenData = MutableLiveData<Int>()
        val strData = MutableLiveData<String>()
        Transformations.switchMap(strData) {
            if (it.length > 10)evenData
            else oddData
        }.observe(this, Observer {
            Toast.makeText(this, "now data : $it" , Toast.LENGTH_LONG).show()
        })
        strData.value = "ok"
        oddData.value = 1
        evenData.value = 2
    }

    fun merge(){
        val oddData = MutableLiveData<Int>()
        val evenData = MutableLiveData<Int>()
        val manger = MediatorLiveData<Int>()
        manger.addSource(oddData){

        }
        manger.addSource(evenData){

        }
        manger.observe(this, Observer {
            Toast.makeText(this, "now data : $it from merge" , Toast.LENGTH_LONG).show()
        })
    }


}