package com.example.aac.databinding

class User{
    var name: String = ""
    var password: String = ""

    constructor(name: String, password: String) {
        this.name = name
        this.password = password
    }

    companion object{
        fun up(name: String):String{
            return name.toUpperCase()
        }
    }
}