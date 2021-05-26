package com.xwpeng.tkotlin.singleton

/**
 * 双重检测锁模式
 */
class Singleton4 private constructor(){
    companion object {
        val instance: Singleton4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton4() }
    }
}

/**
 * 带参数的双重检测锁模式
 */
class SingletonDemo private constructor(private val property: Int) {//这里可以根据实际需求发生改变

    companion object {
        @Volatile private var instance: SingletonDemo? = null
        fun getInstance(property: Int) =
                instance ?: synchronized(this) {
                    instance ?: SingletonDemo(property).also { instance = it }
                }
    }
}