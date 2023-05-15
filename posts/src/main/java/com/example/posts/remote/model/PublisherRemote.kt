package com.example.posts.remote.model

import com.squareup.moshi.Json

data class PublisherRemote(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)
