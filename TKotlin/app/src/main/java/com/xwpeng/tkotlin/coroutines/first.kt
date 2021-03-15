package com.xwpeng.tkotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

//fun main(){
//runBlocking()
//}

suspend fun main(){
    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello, ")
    job.join()
}
//fun main() = kotlinx.coroutines.runBlocking {
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    delay(2000L)
//}

private fun coroutinesDelay() {
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    //阻塞主线程2秒钟保证JVM存活
    Thread.sleep(2000L)
}

private fun threadDelay() {
   thread {
        Thread.sleep(1000L)
        println("World!")
    }
    println("Hello,")
    //阻塞主线程2秒钟保证JVM存活
    Thread.sleep(2000L)
}

private fun runBlocking(){
    GlobalScope.launch {
        delay(1000L)
        println("World")
    }
    println("Hello,")
    //直到里边的协程结束才结束
    kotlinx.coroutines.runBlocking {
        delay(2000L)
    }
}