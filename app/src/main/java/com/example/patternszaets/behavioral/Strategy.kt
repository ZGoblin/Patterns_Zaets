package com.example.patternszaets.behavioral

import java.util.*

interface StringChanger {
    fun refactor(string: String) : String
}

class CamelCase : StringChanger {
    override fun refactor(string: String): String {
        val words = string.split(" ")
        val capWords = words.map {
            it.capitalize()
        }
        return capWords.joinToString("")
    }
}

class SnakeCase : StringChanger {
    override fun refactor(string: String): String {
        val words = string.split(" ")
        val capWords = words.map {
            it.toLowerCase()
        }
        return capWords.joinToString("_")
    }
}

class ConstCase : StringChanger {
    override fun refactor(string: String): String {
        val words = string.split(" ")
        val capWords = words.map {
            it.toUpperCase()
        }
        return capWords.joinToString("_")
    }
}

class Refactorer() {
    private var stringChanger: StringChanger? = null

    fun refactor(string: String) : String = stringChanger?.refactor(string) ?: ""

    fun setRefactoringMethod(stringChanger: StringChanger) {
        this.stringChanger = stringChanger
    }
}

fun main() {
    val string = "HEllo Earth and Sun"
    val refactorer = Refactorer()
    when {
        string[0] == string[0].toUpperCase() && string[1] == string[1].toUpperCase() -> refactorer.setRefactoringMethod(ConstCase())
        string[0] == string[0].toUpperCase() && string[1] == string[1].toLowerCase() -> refactorer.setRefactoringMethod(CamelCase())
        string[0] == string[0].toLowerCase() && string[1] == string[1].toLowerCase() -> refactorer.setRefactoringMethod(SnakeCase())
    }
    println(refactorer.refactor(string))
}