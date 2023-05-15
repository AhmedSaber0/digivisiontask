package com.example.posts.data.datasource

import com.example.posts.data.model.PostsResultEntity
import io.reactivex.rxjava3.core.Single

interface PostsRemoteDataSource {

    fun getPosts(pageSize: Int): Single<PostsResultEntity>
}
