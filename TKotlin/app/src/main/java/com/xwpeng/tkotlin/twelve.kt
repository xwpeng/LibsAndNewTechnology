package com.xwpeng.tkotlin.twelve

import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredFunctions

fun main() {
//    testAnnoProcessing()
//    testReference()
//    fooA()
//    fooB()
    val container = Container(mutableListOf<Int>(1,3,2,5,4,7,6))
    val kClass = container::class
    val typeParameters = kClass.typeParameters
     println(typeParameters)
    val kTypeParameterName =  typeParameters[0]
    println(kTypeParameterName.isReified)
    println(kTypeParameterName.name)
    println(kTypeParameterName.upperBounds)
    println(kTypeParameterName.variance)
    //获取构造函数信息
    val constructors = kClass.constructors
    for (kfunction in constructors) {
        kfunction.parameters.forEach {
            val name = it.name
            val type = it.type
            println("name = ${name}")
            println("type = ${type}")
            for (KTypeProjection in type.arguments){
                println(KTypeProjection.type)
            }
        }
    }

}

class A<T>

open class C<T>
class B<T> : C<Int>()

fun fooA() {
    val parameterizedType = A<Int>()::class.java.genericSuperclass as ParameterizedType
    val actualTypeArguments = parameterizedType.actualTypeArguments
    for (type in actualTypeArguments) {
        val typeName = type.javaClass.name
        println("typeName = ${typeName}")
    }
}

fun fooB() {
    val parameterizedType = B<Int>()::class.java.genericSuperclass as ParameterizedType
    val actualTypeArguments = parameterizedType.actualTypeArguments
    for (type in actualTypeArguments) {
        val typeName = type.typeName
        println("typeName = ${typeName}")
    }
    val a = 1 
    println(a::class.java)
}

private fun testReference() {
    //类引用
    val container = Container(mutableListOf<Int>(1, 2, 3, 4, 5, 6))
    val kClass = container::class
    val jClass = container.javaClass
    val jKClass = kClass.java
    println(jClass === jKClass)//true
    //函数引用
    fun isOdd(x: Int) = x % 2 != 0
    isOdd(7)
    isOdd(2)
    val nums = listOf(1, 2)
    val filteredNums = nums.filter(::isOdd)
    println(filteredNums)
    //::isOdd 就是一个函数类型（Int）->Boolean
    //属性引用
    testReflectProperty()
    //绑定函数和属性引用
    val digitRegex = "\\d+".toRegex()
    println(digitRegex.matches("7"))
    println(digitRegex.matches("6"))
    println(digitRegex.matches("5"))
    println(digitRegex.matches("X"))

    val isDigit = digitRegex::matches
    println(isDigit("7"))
    println(isDigit("6"))
    println(isDigit("5"))
    println(isDigit("X"))
}

fun testReflectProperty() {
    //定义在文件中才能引用,KProperty0,KMutableProperty0
    println(::one.get())
    ::one.set(2)
    println(one)
}

var one = 1


open class BaseContainer<T>

class Container<T : Comparable<T>> : BaseContainer<Int> {
    var elements: MutableList<T>

    constructor(elements: MutableList<T>) {
        this.elements = elements
    }

    fun sort(): Container<T> {
        elements.sort()
        return this
    }

    override fun toString(): String {
        return "Container (elements = $elements)"
    }
}

private fun testAnnoProcessing() {
    val swordTest = SwordTest()
    //获取kclass
    val kclass = swordTest::class
    //获取类上的注解信息
    kclass.annotations.forEach {
        println(it)
    }
    //获取定义的函数信息
    val declareFunctions = kclass.declaredFunctions
    //获取函数上的注解信息
    for (f in declareFunctions) {
        f.annotations.forEach() {
            println(it)
            if (it is TestCase) {
                val id = it.id
                println("testcast annotaion id ${id}")
                //反射调用方法
                f.call(swordTest, id)
            }
        }
    }
    println(kclass)
}

@Target(AnnotationTarget.CLASS
        , AnnotationTarget.FUNCTION
        , AnnotationTarget.VALUE_PARAMETER
)
//, AnnotationTarget.EXPRESSION   表达式提示仅SOURCE能声明，RUNTIME不行
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class TestCase(val id: String)

@Target(AnnotationTarget.CLASS
        , AnnotationTarget.FUNCTION
        , AnnotationTarget.VALUE_PARAMETER
)
//, AnnotationTarget.EXPRESSION   表达式提示仅SOURCE能声明，RUNTIME不行
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class Run

@Run
class SwoedTest {}

@Run
class SwordTest {
    @TestCase(id = "1")
    fun testCase(testId: String) {
        println("Run SwordTest ID = ${testId}")
    }
}

annotation class AnnoX(val value: String)
annotation class AnnoY(val message: String, val annoX: AnnoX = AnnoX("X"))

