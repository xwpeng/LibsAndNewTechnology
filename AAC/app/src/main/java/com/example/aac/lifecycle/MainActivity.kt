package com.example.aac.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.example.aac.R
import com.example.aac.mvp.TestCtr
import java.lang.Thread.sleep

/**
 * 生命周期多个方法调用顺序？
 *顺时针如onResume拥有者生命周期内方法走完才会去走观察者标记的方法,逆时针如onDestroy会先执行外部再执行拥有者的
 * 有多个观察者？
 * 同个观察者相同标记顺序按照方法名排序执行
 */
class MainActivity : TestCtr.View, AppCompatActivity() {
    private lateinit var mPresenter: TestCtr.Presenter
    private lateinit var mSelfTestSelfLifecycleOwner: TestSelfLifecycleOwner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("xwpeng16", "onCreate开始准备工作。。。")

        mPresenter = Presenter(this)
        lifecycle.addObserver(mPresenter)
        val f:Fragment;
//        mSelfTestSelfLifecycleOwner = TestSelfLifecycleOwner()
//        mSelfTestSelfLifecycleOwner.lifecycle.addObserver(mPresenter)
        Log.e("xwpeng16", "onCreate完成准备工作。。。")
    }

    override fun onResume() {
        super.onResume()
        Log.e("xwpeng16", "super.onResume 执行了")
    }

    override fun onPause() {
        super.onPause()
        Log.e("xwpeng16", "super.onPause 执行了")
        lifecycle.addObserver(MyObserver())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("xwpeng16", "super.onDestroy 执行了")
        mSelfTestSelfLifecycleOwner.onDestroy()
    }

    class Presenter(val view: TestCtr.View) : TestCtr.Presenter {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        override fun netData2(owner: LifecycleOwner?) {
            Log.e("xwpeng16", "持有owner： $owner")
            Log.e("xwpeng16", "执行netData2，获取网络数据2。。。")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        override fun netData3(owner: LifecycleOwner) {
            Thread {
                sleep(30000)
                if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED))
                    Log.e("xwpeng16", "执行netData3，获取网络数据3。。。")
            }.start()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        override fun netData(owner: LifecycleOwner?) {
            Log.e("xwpeng16", "持有owner： $owner")
            Log.e("xwpeng16", "执行netData，获取网络数据。。。")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        override fun stopAnim() {
            Log.e("xwpeng16", "执行stopAnim，停止动画。。。")
        }

        override fun unsubscribe() {
            Log.e("xwpeng16", "执行unsubscribe，结束耗时任务。。。")
        }


    }
}