package com.xwpeng.tkotlin.eleven

import java.math.BigDecimal

fun main() {
    //    one()
//    two()
//    three()
    four()
}

private fun four() {
    val p1 = Point(1, 1)
    val p2 = Point(1, 1)
    val p3 = Point(3, 2)
    println(p1 >= p2)
    println(p1 >= p3)
}

class Point(val x: Int, val y: Int) {
    operator fun compareTo(other: Point): Int {
        val thisNorm = Math.sqrt((this.x * this.x + this.y * this.y).toDouble())
        val otherNorm = Math.sqrt((other.x * other.x + other.y * other.y).toDouble())
        return thisNorm.compareTo(otherNorm)
    }
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE
operator fun BigDecimal.dec() = this - BigDecimal.ONE

private fun three() {
    var bigDecimall = BigDecimal(100)
    var bigDecimall2 = BigDecimal(100)
    val temp1 = bigDecimall++
    val temp2 = ++bigDecimall2
    println(temp1)
    println(temp2)
    println(bigDecimall)
    println(bigDecimall2)

    var bigDecimall3 = BigDecimal(100)
    var bigDecimall4 = BigDecimal(100)
    val temp3 = bigDecimall3--
    val temp4 = --bigDecimall4
    println(temp3)
    println(temp4)
    println(bigDecimall3)
    println(bigDecimall4)
}


private fun two() {
    val c1 = Complex(1, 1)
    val c2 = Complex(2, 2)
    val p = c1 + c2
    val m = c1 - c2
    val t = c1 * c2
    println(p)
    println(m)
    println(t)
}

private fun one() {
    val a = BoxInt(3)
    val b = BoxInt(7)
    println(a + b)
    println(a * b)
}

class BoxInt(var i: Int) {
    operator fun times(x: BoxInt) = BoxInt(i * x.i)
    override fun toString(): String {
        return i.toString()
    }
}

operator fun BoxInt.plus(x: BoxInt) = BoxInt(this.i + x.i)

/**
 * 复数，实现它的加减乘
 */
class Complex(var real: Int, var image: Int) {

    operator fun plus(c: Complex): Complex {
        return Complex(c.real + this.real, c.image + this.image)
    }

    operator fun minus(c: Complex): Complex {
        return Complex(this.real - c.real, this.image - c.image)
    }

    operator fun times(c: Complex): Complex {
        return Complex(this.real * c.real - this.image * c.image,
                this.image * c.real + c.image * this.image)
    }

    override fun toString(): String {
        return real.toString() + (if (image > 0) "+" else "") + image + "i"
    }
}

