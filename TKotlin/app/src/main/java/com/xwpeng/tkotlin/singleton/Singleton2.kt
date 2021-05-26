package com.xwpeng.tkotlin.singleton

/**
 * 懒汉模式
 */
class Singleton2 private constructor() {
    companion object {
        private var instance: Singleton2? = null
        get() {
            if(field == null) field = Singleton2()
            return field
        }
        fun get():Singleton2{
            return instance!!
        }
    }
}