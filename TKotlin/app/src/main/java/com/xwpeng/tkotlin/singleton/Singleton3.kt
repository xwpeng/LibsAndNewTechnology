package com.xwpeng.tkotlin.singleton

/**
 * 懒汉线程安全模式
 */
class Singleton3 private constructor() {
    companion object {
        private var instance: Singleton3? = null
            get() {
                return field ?: Singleton3()
            }

        @Synchronized
        fun get(): Singleton3 {
            return instance!!
        }
    }
}