package com.xwpeng.tkotlin

import kotlin.concurrent.thread

fun main() {
//    val one = NestedClassedDemo.Outer().one
//    val two = NestedClassedDemo.Outer.Nested().getTwo()
//    val three = NestedClassedDemo.Outer.Nested.Nested1().three
//    val four = NestedClassedDemo.Outer.Nested.Nested1().getFour()
//    println(one)
//    println(two)
//    println(three)
//    println(four)
//    println(Outer2().Nested().two)
    NonameInnerClassDemo().dorun()
}

fun num(num:Int){
    var num = 2
    if (num > 0) {
        var num = 3
    }
}

class NestedClassedDemo {
    class Outer {
        private val zero: Int = 0
        val one: Int = 1

        class Nested {
            fun getTwo(): Int {
                return 2
            }

            class Nested1 {
                var three = 3

                constructor(three: Int) {
                    this.three = three
                }


                fun getFour() = 4


            }
        }
    }
}

class Outer2 {
    private val one = 1

    inner class Nested {
        val two = 2;
        fun a() = {
            print(one)
        }
       inner class Nested1(){

        }
    }
}

class NonameInnerClassDemo{
    var isRuning = false
    fun dorun(){
        class A : Runnable{
            override fun run() {
            }

        }
        val funa = Runnable {

        }
        Thread(funa).start()
        Thread(A()).start()
        Thread(Runnable { kotlin.run {

        } }).start()
        Thread({

        }).start()
        val wait = {

        }
        Thread(wait).start()
        Thread(object : Runnable{
            override fun run() {
            }
        })
        thread {  }
    }
}