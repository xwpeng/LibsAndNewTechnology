package com.example.aac.livedata.bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.aac.livedata.bus.LiveDataBus.ObserverWrapper

/**
 * ObserveForever还是需要手动移除
 * 需要注意线程切换的问题
 * 已经有类型混乱的情况了
 */
class LiveDataBus private constructor() {
    private val mLiveDatas: MutableMap<Event<*>, MutableLiveData<*>> by lazy {
        mutableMapOf<Event<*>, MutableLiveData<*>>()
    }

    companion object {
        fun getInstance(): LiveDataBus {
            return Inner.instance
        }
    }


    private object Inner {
        val instance = LiveDataBus()
    }

    @Synchronized
    fun <T> getChannel(type: Event<T>): BusMutableLiveData<T>?{
      return  mLiveDatas.getOrPut(type){
            BusMutableLiveData<T>()
        } as? BusMutableLiveData<T>
    }

    @kotlin.Deprecated("这个还是不能限定类型，会造成类型转换失败的可能")
    @Synchronized
    private fun <T>getChannleByString(channel: String): BusMutableLiveData<T>{
        return mLiveDatas.getOrPut(EventList.EVENT1){
            BusMutableLiveData<T>()
        } as BusMutableLiveData<T>
    }




    /**
     * 为处理ObserverForver自定义的Obsetver,在Onchange的时候检查调用栈，避免粘性特性
     */
    class ObserverWrapper<T>(private val observer: Observer<in T>) : Observer<T> {

        override fun onChanged(t: T) {
            if (isCallObserveForever()) return
            observer.onChanged(t)
        }

        private fun isCallObserveForever(): Boolean {
            val stackTrace = Thread.currentThread().stackTrace
            if (stackTrace.isNotEmpty()) {
                stackTrace.forEach {
                    if ("android.arch.lifecycle.LiveData" == it.className
                        && "observeForever" == it.methodName
                    ) return true
                }
            }
            return false
        }

    }

    class BusMutableLiveData<T> : MutableLiveData<T>() {
        //自己管理observeForever，在需要remove的时候要移除，在添加的时候需要判定是否添加了
        private val observeMap: MutableMap<Observer<in T>, LiveDataBus.ObserverWrapper<T>> =
            mutableMapOf()

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun observeForever(observer: Observer<in T>) {
            val exist = observeMap.getOrPut(observer) {
                ObserverWrapper(observer)
            }
            super.observeForever(exist)
        }

        fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>){
            super.observe(owner, observer)
        }

        fun observeForeverSticky(observer: Observer<in T>){
            super.observeForever(observer)
        }

        @Throws
        private fun hook(observer: Observer<in T>) {
            val liveDataclz = LiveData::class.java
            val filedObservers = liveDataclz.getDeclaredField("mObservers")
            filedObservers.isAccessible = true
            val objObservers = filedObservers.get(this)
            val observerClz = objObservers.javaClass
            val methodGet = observerClz.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true
            val objectWrapperEntry = methodGet.invoke(objObservers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("Wrapper can not be bull!")
            }
            val classObserverWrapper = objectWrapper.javaClass.superclass
            val fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion")
            fieldLastVersion.isAccessible = true
            //get livedata's version
            val fieldVersion = liveDataclz.getDeclaredField("mVersion")
            fieldVersion.isAccessible = true
            val objectVersion = fieldVersion.get(this)
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, objectVersion)
        }

        override fun removeObserver(observer: Observer<in T>) {
            var realObserver: Observer<in T>? = observeMap.remove(observer)
            if (realObserver == null) realObserver = observer
            super.removeObserver(realObserver)
        }


    }
}