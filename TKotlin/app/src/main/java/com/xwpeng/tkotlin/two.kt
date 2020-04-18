package com.xwpeng.tkotlin

import java.lang.Integer.parseInt

fun main() {
//    var a: Int;
//    a = max(1, 2)
//    a = max3(1, 2)
//    println(a)
//    println(isLeapYear(2020))
//    casesWhen(1)
//    casesWhen(10)
//    casesWhen("hello")
//    casesWhen(null)
//    sw(1)
//    sw(123)
//    sw(0)
//    sw2()
//    println(fact(5))
//    println(sumFact())
//    treturn()
//    treturn2()
//    tLabel()
//    tThrow()
//    tLabel2()
    println(-Point(1, 1))
    println(Counter(1) + 10)
    tInfix()
}

data class Person(var name:String, var  age:Int)
infix fun Person.grow(year: Int): Person {
    return Person(name, age + year)
}
fun tInfix(){
    val person = Person("Jack", 20)
    println(person.grow(1))
    println(person grow 1)
}

fun max(a: Int, b: Int): Int {
    val max = if (a > b) a else b
    return max
}

fun max3(a: Int, b: Int): Int {
    var max = if (a > b) {
        println("max is a")
        a
    } else {
        println("max is b")
        b
    }
    return max;
}

fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && year % 100 != 0
}

fun casesWhen(obj: Any?) {
    when (obj) {
        in 0..9, 10 -> println("${obj} ====> 这是0-10之间的数字")
        //    0,1,2,3,4,5,6,7,8,9,10 -> println("${obj} ====> 这是0-10之间的数字")
        "hello" -> println("${obj} ===>这是字符串hello")
        is Char -> println("${obj} ===> 这是char字符")
        else -> println("${obj}是default值")
    }
}

fun sw(x: Int) {
    val s = "123"
    when (x) {
        -1, 0 -> println("x == -1 or x == 0")
        1 -> println("x == 1")
        2 -> println("x==2")
        parseInt(s) -> println("x 是 123")
        else -> println("x default")
    }
}

fun sw2() {
    val x = 1
    val validNumber = arrayOf(1, 2, 3)
    when (x) {
        in 1..10 -> println("x is range 1 - 10")
        in validNumber -> println("x is valid")
        !in 0..20 -> println("x not in 0-20")
        else -> println("default path")
    }
}

fun fact(n: Int): Int {
    val result: Int
    when (n) {
        0, 1 -> result = 1
        else -> result = n * fact(n - 1)
    }
    return result
}

fun sumFact(): Int {
    var result = 0
    (1..10).forEach { result += fact(it) }
    return result;
}

fun treturn() {
    val intarray = intArrayOf(1, 2, 3, 4, 5)
    intarray.forEach {
        if (it == 3) return
        println(it)
    }
}

fun treturn2() {
    val intarray = intArrayOf(1, 2, 3, 4, 5)
    intarray.forEach(fun(a: Int) {
        if (a == 3) return
        println(a)
    })
}

fun tLabel() {
    var array1 = arrayOf(1, 2, 3, 4, 5)
    array1.forEach {
        if (it == 3) return@forEach
        println(it)
    }
}

fun tLabel2() {
    var array1 = arrayOf(1, 2, 3, 4, 5)
    array1.forEach here@ {
        if (it == 3) return@here
        println(it)
    }
}

fun tThrow(){
    throw Exception("error")
}

data class Point(val x: Int, val y: Int)
operator fun Point.unaryMinus() = Point(-x, -y)

data class Counter(var index:Int)
operator fun Counter.plus(increment:Int): Counter {
    return Counter(index + increment);
}