package com.example.posts.remote.datasource

import com.example.posts.data.datasource.PostsRemoteDataSource
import com.example.posts.data.model.PostsResultEntity
import com.example.posts.remote.api.PostsApi
import com.example.posts.remote.mapper.PostsEntityMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostsRemoteDataSourceImpl @Inject constructor(
    private val api: PostsApi,
    private val postsEntityMapper: PostsEntityMapper
) : PostsRemoteDataSource {

    override fun getPosts(pageSize: Int): Single<PostsResultEntity> {
        return api.getPosts(pageSize).map { postsEntityMapper.map(it) }
    }
}
