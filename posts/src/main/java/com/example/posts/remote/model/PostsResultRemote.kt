package com.example.posts.remote.model

import com.squareup.moshi.Json

data class PostsResultRemote(
    @field:Json(name = "articles")
    val postsRemotes: List<PostRemote>?,
    @field:Json(name = "status")
    val status: String?,
    @field:Json(name = "totalResults")
    val totalResults: Int?
)
