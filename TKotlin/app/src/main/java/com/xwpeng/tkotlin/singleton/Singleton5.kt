package com.xwpeng.tkotlin.singleton

/**
 * 静态内部类模式
 */
class Singleton5 private constructor(private val a:Int) {
    companion object {
        fun getInstance(a: Int): Singleton5 {
            return Inner.getInstance(a)
        }
    }

    private object Inner {
        fun getInstance(a: Int) : Singleton5{
           return Singleton5(a)
        }
    }
}