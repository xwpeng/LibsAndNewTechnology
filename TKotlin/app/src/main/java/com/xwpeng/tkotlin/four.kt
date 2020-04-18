package com.xwpeng.tkotlin.four

class EmptyClass

fun main() {
//   emptyClass()
//constuct()
//    construc1()
//    construc2()
//    tAbsteact()
    tInterface()
}

/**空类
 */
fun emptyClass() {
    val emptyClass = EmptyClass()
    //变量对象
    println(emptyClass)
    println(emptyClass is EmptyClass)
    //类型对象
    println(emptyClass::class)
}

class Person(var name: String, var age: Int, var sex: String) {
    override fun toString(): String {
        return "Person(name='$name', age=$age, sex='$sex')"
    }
}

fun constuct() {
    val person = Person("xwpeng", 26, "男")
    println("${person}")
}

class Person1 {
    lateinit var name: String
    var age: Int = 0
    lateinit var sex: String
    override fun toString(): String {
        return "Person1(name='$name', age=$age, sex='$sex')"
    }
}

fun construc1() {
    val person = Person1()
    person.name = "xwpeng"
    person.sex = "男"
    println("${person}")
}

class Person2() {
    lateinit var name: String
    var age: Int = 0
    lateinit var sex: String

    constructor(name: String) : this() {
        this.name = name
    }

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int, sex: String) : this() {
        this.name = name
        this.age = age
        this.sex = sex
    }

    override fun toString(): String {
        return "Person2(name='$name', age=$age, sex='$sex')"
    }
}

fun construc2() {
    val p2 = Person2("1")
    p2.sex = "男"
    println("${p2}")
}

abstract class Shape
class Rectangle : Shape()
class Circle : Shape()
class Triangle : Shape()

abstract class Car(open var userId : String) {
    abstract var name: String
    open var userAge: Int = 0
    abstract override fun toString(): String
    open fun click() {
        println("car click")
    }
}

class BaoMa(override var name: String, override var userId: String) : Car(userId) {
    override var userAge:Int = 1
    override fun toString(): String {
        return "BaoMa(name='$name'useage='$userAge'id='$userId')"
    }

    override fun click() {
        println("BaoMa click")
        println("${this::class.simpleName} click")
    }
}

fun tAbsteact() {
    var baoma = BaoMa("aa", "121");
    baoma.userAge = 1
    baoma.click()
    println("${baoma}")
}

interface ProjectService {
    fun save(a:Int)
    fun print(){
        println("ProjectService print")
    }
}
interface MainService {
    val name: String
    val owner: String
    fun save(s: String)
    fun print() {
        println("mainsevice print")
    }
}

class ServiceImpl(override val name: String, override val owner: String) : ProjectService, MainService {
    override fun print() {
        println("ServiceImpl print")
    }

    override fun save(a: Int) {
    }

    override fun save(s: String) {
        super<ProjectService>.print()
        println("save " + s)
    }
}

fun tInterface() {
 ServiceImpl("xwpeng15", "xwpeng16").save("gold")
}