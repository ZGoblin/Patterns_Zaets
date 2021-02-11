package com.example.patternszaets.creational

class Repository private constructor() {
    companion object {
        private val repository = Repository()

        fun getInstance() = repository
    }

    var data: String = "default String"
}

fun setNewDataToRepository(newData: String) {
    val repo = Repository.getInstance()
    repo.data = newData
}

fun main() {
    val repo = Repository.getInstance()

    println(repo.data)

    setNewDataToRepository("Data changed")

    println(repo.data)
}