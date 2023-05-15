package com.example.posts.test.factory

import com.example.core_testing.factory.DataFactory.randomString
import com.example.posts.data.model.PostEntity
import com.example.posts.domain.model.Post

object PostFactory {

    fun makePost() = Post(
        title = randomString(),
        url = randomString()
    )

    fun makePostEntity() = PostEntity(
        title = randomString(),
        url = randomString(),
        createdAt = randomString()
    )

}
