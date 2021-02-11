package com.example.patternszaets.behavioral

import android.accessibilityservice.GestureDescription
import android.provider.ContactsContract

interface Listener {
    fun dataUpdated(string: String)
}

class SubscribersManager {
    private val subscribers = mutableSetOf<Listener>()

    fun subscribe(listener: Listener) {
        subscribers.add(listener)
    }

    fun unsubscribe(listener: Listener) {
        subscribers.remove(listener)
    }

    fun notify(string: String) {
        subscribers.forEach {
            it.dataUpdated(string)
        }
    }
}

class Database {
    val subscribersManager = SubscribersManager()
    var data: String = ""
    set(value) {
        field = value
        subscribersManager.notify(field)
    }
}

class DatabaseListenerFirst : Listener {
    override fun dataUpdated(string: String) {
        println("Database Listener first: $string")
    }
}

class DatabaseListenerSecond : Listener {
    override fun dataUpdated(string: String) {
        println("Database Listener second: $string")
    }
}

fun main() {
    val database = Database()
    val databaseListenerFirst = DatabaseListenerFirst()
    database.subscribersManager.subscribe(databaseListenerFirst)
    database.subscribersManager.subscribe(DatabaseListenerSecond())
    database.data = "Hello "
    database.subscribersManager.unsubscribe(databaseListenerFirst)
    database.data += "World"
}