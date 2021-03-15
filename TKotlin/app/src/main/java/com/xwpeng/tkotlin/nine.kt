package com.xwpeng.tkotlin

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URL

fun main() {
val r1 = Regex("[a-z]+", RegexOption.IGNORE_CASE)
}

private fun shell() {
    val p = "ls -al".execute()
    val exitCode = p.waitFor()
    val text = p.text()
    println(exitCode)
    println(text)
}

fun String.execute(): Process {
    val runtime = Runtime.getRuntime()
    return runtime.exec(this)
}

fun Process.text(): String {
    var output = ""
    val inputStream = this.inputStream
    val isr = InputStreamReader(inputStream)
    val reader = BufferedReader(isr)
    var line: String? = ""
    while (line != null) {
        line = reader.readLine()
        output += line + "\n"
    }
    return output
}

private fun fileOpration() {
    //File("/").walk().iterator().forEach { print(it.absolutePath) }
    fun getUrlContent(url: String): String {
        return URL(url).readText()
    }

    fun getUrlBytes(url: String): ByteArray {
        return URL(url).readBytes()
    }

    fun writeUrlBytesTo(filename: String, url: String) {
        val bytes = URL(url).readBytes()
        File(filename).writeBytes(bytes)
    }

    println(getUrlContent("https://www.baidu.com"))

    writeUrlBytesTo("图片.jpg", "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg")
}
