package com.xwpeng.tkotlin

fun main() {

}

interface Generator<T> {
    fun getData(): T
}

class Generatorr<T> {
    fun getData(t: T): T {
        return t
    }
}

fun <T : Comparable<T>> a(x: T, y: T): Boolean {
    return x > y
}