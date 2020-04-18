package com.xwpeng.tkotlin

fun main() {
//testRun1()
//    tRun2()
//    tWith()
//    tApply()
//    tAlso()
//    testApply()
//    testLetFun()
    testWithFun()
}

fun testRun1() {
    var a = "sss"
    run {
        var a = "bbb"
        println(a)
    }
    println(a)
}

fun tRun2() {
    val str = "kotlin"
    str.run {
        println("length = ${this.length}")
        println("first = ${first()}")
        println("last = ${last()}")
        println(this)
    }
}

fun tWith() {
    var str = "kotlin"
    with(str) {
        println("length = ${this.length}")
        println("first = ${first()}")
        println("last = ${last()}")
        println(this)
    }
}


fun tApply() {
    "kotlin".apply {
        println(this.firstChar())
        println("结果：${this.plus("-java")}")
    }.apply {
        println("结果：${this.plus("-php")}")
    }
}

fun tAlso() {
    "kotlin".also {
        println(it.first())
        println("结果：${it.plus("-java")}")
        println("结果：${it.plus("-java")}")
    }.also {
        println("结果：${it.plus("-php")}")
    }

    "kotlin".apply {
        println("结果：${this.plus("-java")}")
    }.apply {
        println("结果：${this.plus("-php")}")
    }
}

fun testApply() {
    val list = mutableListOf<String>()
    list.add("A")
    list.add("B")
    list.add("C")
    println("普通写法 list = $list")
    println(list)

    val a = ArrayList<String>().apply {
        add("A")
        add("B")
        add("C")
        println("使用apply函数写法this = $this")
    }
    println(a)
    a.let { println(it) }
}

fun testLetFun() {
    1.let { println(it) }
    "ABC".let { println(it) }
    myfun().let { println(it) }
}

fun myfun(): String {
    println("执行了myFun")
    return "这是myFun的返回值"
}

fun testWithFun() {
    //普通写法
    val list = mutableListOf<String>()
    list.add("A")
    list.add("B")
    list.add("C")
    println("常规写法 list = $list")

    //使用with()函数写法
    with(ArrayList<String>()) {
        add("A")
        add("B")
        add("C")
        println(this)
    }.let { println(it) }
}