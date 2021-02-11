package com.example.patternszaets.creational

data class Song (
    val singer: String,
    val name: String
    )

interface MusicSource {
    fun getPlaylist() : Set<Song>
}

class InternetSource : MusicSource {
    override fun getPlaylist() : Set<Song> {
        return setOf<Song>(Song("AC/DC", "Back In Black"),
            Song("The Score", "Legends"),
            Song("Sabaton", "The Attack of the dead men"),
            Song("Linkin Park", "Numb"),
            Song("Smash Mouth", "All Star"),
        )
    }
}

class LocalSource : MusicSource {
    override fun getPlaylist() : Set<Song> {
        return setOf<Song>(Song("Five Finger Death Punch", "Wrong side of heaven"),
            Song("Imagine dragons", "thunder"),
            Song("Kevin Rudolf", "In the city"),
            Song("Alice Merton", "No roots"),
            Song("Caravan Palace", "Lone Digger"),
        )
    }
}

abstract class MusicStorage {
    fun getPlaylist() : Set<Song> {
        val musicSource = createMusicSource()

        return musicSource.getPlaylist()
    }

    abstract fun createMusicSource() : MusicSource
}

class LocalStorage : MusicStorage() {
    override fun createMusicSource(): MusicSource = LocalSource()
}

class RemoteStorage : MusicStorage() {
    override fun createMusicSource(): MusicSource = InternetSource()
}

fun main() {
    val storageRemote: MusicStorage = RemoteStorage()
    val storageLocal: MusicStorage = LocalStorage()

    println("Remote: ${storageRemote.getPlaylist()}")
    println("Local: ${storageLocal.getPlaylist()}")
}
