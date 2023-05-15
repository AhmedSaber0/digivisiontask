package com.example.posts.domain.repository

import com.example.posts.domain.model.Post
import io.reactivex.rxjava3.core.Single

interface PostsRepository {

    fun getPosts(pageSize : Int): Single<List<Post>>
}
