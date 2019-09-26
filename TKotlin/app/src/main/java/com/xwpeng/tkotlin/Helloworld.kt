package com.xwpeng.tkotlin


fun main() {
    println("Hello world")
    var b: String? = "abc"
    println(b?.length)
    b = null
    println(b?.length)
    println("abc".firstChar())
}

fun String.firstChar(): String {
    if (this.isEmpty()) return ""
    return this[0].toString()
}