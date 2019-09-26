package com.xwpeng.tkotlin

fun main() {
    println(strlen("xwpeng"))
    println(strlen(1))
    println(strlen(true))
    tas()
}

fun strlen(ani :Any):Int{
    if (ani is String) return ani.length
    else if (ani is Int) return 1
    return -1
}

open class Foo
class Goo:Foo()

fun tas(){
    val foo = Foo()
    val goo = Goo()
    println(foo as Goo)
    println(foo as? Goo)
    println(goo as Foo)
}