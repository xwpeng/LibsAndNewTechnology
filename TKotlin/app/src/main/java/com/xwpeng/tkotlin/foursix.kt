package com.xwpeng.tkotlin
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Ok

@Ok
class A(){
    val a = ""
}

enum class Direction{
    NORTH,SOUTH,WEST,EAST
}

enum class Color(val rgb:Int) {
    RED(0xFF000),
    GREEN(0x00ff00),
    BLUE(0x0000ff)
}

fun main(){
    val north = Direction.NORTH;
    println(north.name)
    println(north.ordinal)
    println(north is Direction)
    println("${north}")

    val c = Color.GREEN
    println(c)
    println(c.rgb)
    println(c.name)
    println(c.ordinal)
}
