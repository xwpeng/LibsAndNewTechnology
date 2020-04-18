package com.xwpeng.tkotlin.fourfour

fun main() {
//    println(User.name)
//    User.name = "sss"
//    println(User.name)
//    println(User.pwd)
//    User.hello()
//    DataProcessor.process()
//    DataProcessor.a = "xws"
//    println(DataProcessor.a)
    println(Person("xwpeng", 12, "男").toString())
////    解构
//    val (a, b, c) = Person("xwpeng", 12, "男")
//    println(a)
//    println(b)
//    println(c)
//    tPair()
}

object User {
    var name: String = "xwpeng"
    val pwd: String = "sendloopok"
    fun hello() {
        println("hello word")
    }
}

class DataProcessor {
    companion object DataProcess {
        var a:String = "s"
        fun process() {
            println("i am data processor's process")
        }
    }
}

data class Person(var name: String, var age: Int, var sex: String) {

//    override fun toString(): String {
//        return "Person(name='$name',age='$age',sex='$sex')"
//    }
}

fun tPair() {
    val map = mapOf<Int, String>(1 to "A", 2 to "B", 3 to "C")
    println("$map")
    Pair(1, 2)
    1 to 2
    Triple(1,2,3)
}