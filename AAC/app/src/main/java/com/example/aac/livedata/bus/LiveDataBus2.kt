package com.example.aac.livedata.bus

import androidx.annotation.MainThread
import androidx.lifecycle.*

class LiveDataBus2 private constructor() {


    internal val liveDatas by lazy { mutableMapOf<Event<*>, MutableLiveData<*>>() }

    @Synchronized
     fun <T> getChannel(event: Event<T>): ExternalLiveData<T> {
        return liveDatas.getOrPut(event) {
            ExternalLiveData<T>(event)
        } as ExternalLiveData<T>
    }

    companion object {
        fun getInstance(): LiveDataBus2 {
            return Inner.instance
        }
    }

    private object Inner {
        val instance = LiveDataBus2()
    }

     class ExternalLiveData<T>(private val event: Event<T>) : MutableLiveData<T>() {
        companion object {
            const val START_VERSION = -1
        }

        internal var mVersion = START_VERSION

        override fun setValue(value: T) {
            mVersion++
            super.setValue(value)
        }

        internal var mObservers = mutableMapOf<Observer<in T>, ExternalObserverWrapper<T>>()

        @MainThread
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            val exist = mObservers.getOrPut(observer) {
                LifecycleExternalObserver(observer, this, owner).apply {
                    owner.lifecycle.addObserver(this)
                }
            }
            super.observe(owner, exist)
        }

        @MainThread
        override fun observeForever(observer: Observer<in T>) {
            val exist = mObservers.getOrPut(observer) {
                AlwaysExternalObserver(observer, this)
            }
            super.observeForever(exist)
        }

        @MainThread
        fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
        }

        @MainThread
        fun observeStickyForever(observer: Observer<in T>) {
            super.observeForever(observer)
        }

        @MainThread
        override fun removeObserver(observer: Observer<in T>) {
            val exist = mObservers.remove(observer) ?: observer
            super.removeObserver(exist)
        }

        @MainThread
        override fun removeObservers(owner: LifecycleOwner) {
            mObservers.iterator().forEach { item ->
                if (item.value.isAttachedTo(owner)) {
                    mObservers.remove(item.key)
                }
            }
            super.removeObservers(owner)
        }

        override fun onInactive() {
            super.onInactive()
            if (!hasObservers()) {
                // 当对应liveData没有相关的观察者的时候
                // 就可以移除掉维护的LiveData
                LiveDataBus2.getInstance().liveDatas.remove(event)
            }
        }
    }

    internal open class ExternalObserverWrapper<T>
        (val observer: Observer<in T>, val liveData: ExternalLiveData<T>) : Observer<T> {

        private var mLastVersion = liveData.mVersion

        override fun onChanged(t: T) {
            if (mLastVersion >= liveData.mVersion) {
                return
            }
            mLastVersion = liveData.mVersion
            observer.onChanged(t)
        }

        open fun isAttachedTo(owner: LifecycleOwner) = false
    }

    /**
     * always active 的观察者包装类
     * @param T
     * @constructor
     */
    internal class AlwaysExternalObserver<T>(
        observer: Observer<in T>,
        liveData: ExternalLiveData<T>
    ) :
        ExternalObserverWrapper<T>(observer, liveData)


    /**
     * 绑定生命周期的观察者包装类
     * @param T
     * @property owner LifecycleOwner
     * @constructor
     */
    internal class LifecycleExternalObserver<T>(
        observer: Observer<in T>,
        liveData: ExternalLiveData<T>,
        private val owner: LifecycleOwner
    ) : ExternalObserverWrapper<T>(observer, liveData), LifecycleObserver {
        /**
         * 当绑定的lifecycle销毁的时候
         * 移除掉内部维护的对应观察者
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            liveData.mObservers.remove(observer)
            owner.lifecycle.removeObserver(this)
        }

        override fun isAttachedTo(owner: LifecycleOwner): Boolean {
            return owner == this.owner
        }
    }


}