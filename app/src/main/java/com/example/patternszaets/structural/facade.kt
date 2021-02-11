package com.example.patternszaets.structural

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Post (
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

interface PostService {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}

class Posts {
    fun getPosts() : List<Post> {
        val postService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)

        return postService.getPosts().execute().body() ?: listOf()
    }
}

fun main() {
    val posts = Posts()
    println(posts.getPosts())
}