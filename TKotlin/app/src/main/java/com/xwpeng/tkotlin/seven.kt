package com.xwpeng.tkotlin

fun main(){
    val list = listOf(1,2,3,4,5)
    val mutableList = mutableListOf<Int>(1,2,3,4,5)

    //重复元素会过滤掉
    val set = setOf<Int>(1, 2,2,3,5)
    val mutableSet = mutableSetOf<Int>(1, 2, 3, 3, 5)

    println(set)
    println(mutableSet)

    //重复key会后覆盖前
    val map = mapOf(1 to "a", 2 to "b", 3 to "c", 3 to "s")
    val mutableMap = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

    println(map)
    println(mutableMap)

    val emptyList:List<Int> = listOf()
    print(emptyList)

    val  emptySet:Set<Int> = setOf()
    print(emptySet)

    val emptyMap:Map<Int,String> = mapOf()
    println(emptyMap)

    list.forEach{
        println(it)
    }

    set.forEach{
        println(it)
    }

    map.forEach{
        println(it)
    }
}