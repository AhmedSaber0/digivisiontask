package com.example.posts.remote.model

import com.squareup.moshi.Json

data class PostRemote(
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "published_date")
    val published_date: String?,
    @field:Json(name = "publisher")
    val publisher: PublisherRemote?
)
