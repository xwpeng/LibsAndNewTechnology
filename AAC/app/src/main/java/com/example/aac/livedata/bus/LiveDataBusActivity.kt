package com.example.aac.livedata.bus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.aac.R

class LiveDataBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedatabus)
//        liveDataBus1Test()
//        liveDataBus1TestSticky()
//        liveDataBus2Test()
        liveDataBus2TestSticky()
    }

    /**
     * 测试LiveDataBus常规事件
     */
    private fun liveDataBus1Test() {
        LiveDataBus.getInstance()
            .getChannel(EventList.EVENT1)
            ?.value = "first"

        LiveDataBus.getInstance()
            .getChannel(EventList.EVENT1)
//            ?.observe(this, Observer {
            ?.observeForever(Observer {
                Toast.makeText(this, "accept event1: $it", Toast.LENGTH_LONG).show()
            })

        Thread {
            (1..10).forEach {
                Thread.sleep(5000)
                LiveDataBus.getInstance()
                    .getChannel(EventList.EVENT1)
                    ?.postValue("" + System.currentTimeMillis())
            }
        }.start()
    }

    /**
     * 测试LiveData粘性事件
     */
    private fun liveDataBus1TestSticky() {
        LiveDataBus.getInstance()
            .getChannel(EventList.EVENT1)
            ?.value = "first"
        val ob: Observer<String> = Observer {
            Toast.makeText(this, "accept event1: $it", Toast.LENGTH_LONG).show()
        }
        LiveDataBus.getInstance()
            .getChannel(EventList.EVENT1)
//            ?.observeSticky(this, Observer {
            ?.observeForeverSticky(ob)
        LiveDataBus.getInstance().getChannel(EventList.EVENT1)?.removeObserver(ob)

        Thread {
            (1..10).forEach {
                Thread.sleep(5000)
                LiveDataBus.getInstance()
                    .getChannel(EventList.EVENT1)
                    ?.postValue("" + System.currentTimeMillis())
            }
        }.start()
    }


    /**
     * 测试LiveDataBus2常规事件
     */
    private fun liveDataBus2Test() {
        LiveDataBus2.getInstance()
            .getChannel(EventList.EVENT1)
            .setValue("first")

        LiveDataBus2.getInstance()
            .getChannel(EventList.EVENT1)
             .observe(this, Observer {
                 Toast.makeText(this, "accept event1: $it", Toast.LENGTH_LONG).show()
            })

        Thread {
            (1..10).forEach {
                Thread.sleep(5000)
                LiveDataBus2.getInstance()
                    .getChannel(EventList.EVENT1)
                    .postValue("" + System.currentTimeMillis())
            }
        }.start()
    }


    /**
     * 测试LiveDataBus2粘性事件
     */
    private fun liveDataBus2TestSticky() {
        LiveDataBus2.getInstance()
            .getChannel(EventList.EVENT1)
            .setValue("first")

        LiveDataBus2.getInstance()
            .getChannel(EventList.EVENT1)
            .observeSticky(this, Observer {
                Toast.makeText(this, "accept event1: $it", Toast.LENGTH_LONG).show()
            })

        Thread {
            (1..10).forEach {
                Thread.sleep(5000)
                LiveDataBus2.getInstance()
                    .getChannel(EventList.EVENT1)
                    .postValue("" + System.currentTimeMillis())
            }
        }.start()
    }


}