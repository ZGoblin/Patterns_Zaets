package com.example.patternszaets.structural

interface Transport {
    fun getCurrentSpeed() : Int
    fun setSpeed(speed: Int)
    fun turn(turn: String)
}

class Driver(private val transport: Transport) {
    fun turnLeft() {
        transport.turn("left")
    }
    fun turnRight() {
        transport.turn("right")
    }
    fun increaseSpeed() {
        transport.setSpeed(transport.getCurrentSpeed() + 10)
    }
    fun reduceSpeed() {
        transport.setSpeed(transport.getCurrentSpeed() - 10)
    }
    fun handBrake() {
        transport.setSpeed(0)
    }
}

class Car : Transport {
    private var speed: Int = 0

    override fun getCurrentSpeed() : Int {
        return speed
    }

    override fun setSpeed(speed: Int) {
        if (speed >= 0) this.speed = speed
        println("Current speed ${this.speed}")
    }

    override fun turn(turn: String) {
        println("Car turn $turn")
    }
}

class Plane : Transport {
    private var speed: Int = 0
    private var high: Int = 0

    override fun getCurrentSpeed() : Int {
        return speed
    }

    override fun setSpeed(speed: Int) {
        if (speed >= 0) {
            if (this.speed <= speed) {
                this.speed = speed
                high += 100
                println("Current speed ${this.speed}, high was increased")
            } else {
                high -= 100
                println("Current speed ${this.speed}, high was reduced")
            }
        } else {
            println("Current speed ${this.speed}")
        }
    }

    override fun turn(turn: String) {
        println("Plane lurch $turn")
    }
}


fun main() {
    var driver = Driver(Car())

    driver.increaseSpeed()
    driver.turnRight()

    driver = Driver(Plane())
    driver.turnRight()
    driver.increaseSpeed()
}