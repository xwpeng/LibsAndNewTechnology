package com.xwpeng.tkotlin

import java.io.*


fun main() {
// aa()
// bb()
    val students = listOf(Student("jack", "M", 90)
            , Student("alice", "F", 70)
            , Student("bob", "M", 60)
            , Student("bill", "M", 80)
            , Student("helen", "F", 100))
    val queryResult = students.select()
            .where { it.score > 70 }
            .and { it.sex == "M" }
    println(queryResult)
}

data class Student(var name: String, var sex: String, var score: Int)

fun <E> List<E>.select(): List<E> = this
fun <E> List<E>.where(predicate: (E) -> Boolean): List<E> {
    val list = this
    val result = arrayListOf<E>()
    list.forEach {
        if (predicate(it)) result.add(it)
    }
    return result
}

fun <E> List<E>.and(predicate: (E) -> Boolean): List<E> {
    return where(predicate)
}


private fun bb() {
    val lines = "E:\\LibsAndNewTechnology-master\\LibsAndNewTechnology-master\\TKotlin\\app\\src\\main\\assets\\data.txt"
            .stream()
            .buffered()
            .reader(Charsets.UTF_8)
            .readLines()
    lines.forEach(::println)
}

private fun aa() {
    class Hello {
        operator fun invoke(name: String) {
            println("Hello, $name")
        }
    }

    val hello = Hello()
    hello("World")
}

fun String.stream() = FileInputStream(this)
fun FileInputStream.buffered() = BufferedInputStream(this)
//这个本来就有一个了
fun InputStream.reader(charset: String) = InputStreamReader(this, charset)

fun Reader.readLines(): List<String> {
    val result = arrayListOf<String>()
    forEachLine { result.add(it) }
    return result
}


