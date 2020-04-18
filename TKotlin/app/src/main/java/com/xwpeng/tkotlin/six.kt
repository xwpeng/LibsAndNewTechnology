package com.xwpeng.tkotlin

fun main() {
    var str: String? = ""
    println(str?.me())
    println(str?.lastChar())
    val list = mutableListOf(1, 2, 3, 4, 5, 6, 7)
    println(list.filter {
        it % 2 == 1
    })
    println(list.firstElement)
    println(list.lastElement)
    list.firstElement = -1
    list.lastElement = -1
    println(list)
}

fun String.me(): String {
    if (this.length == 0) return "me know null"
    return "me know " + this;
}

fun String.lastChar(): String {
    if (this.length == 0) return "null"
    return this[this.lastIndex].toString()
}

//interface Predicate<T> {
//    fun predicate(t: T): Boolean
//}

fun String.isChar(): Boolean = length == 1

fun <T> List<T>.filter(predicate: (T) -> Boolean): MutableList<T> {
    val result = ArrayList<T>()
    this.forEach {
        if (predicate(it)) result.add(it)
    }
    return result
}

var <T> MutableList<T>.firstElement: T
    get() = this[0]
    set(value) {
        this[0] = value
    }

var <T> MutableList<T>.lastElement: T
    get() = this[lastIndex]
    set(value) {
        this[lastIndex] = value
    }
