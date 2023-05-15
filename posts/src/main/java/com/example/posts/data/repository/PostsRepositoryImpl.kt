package com.example.posts.data.repository

import com.example.posts.data.datasource.PostsRemoteDataSource
import com.example.posts.data.mapper.PostsResultMapper
import com.example.posts.domain.model.Post
import com.example.posts.domain.repository.PostsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsRemoteDataSource: PostsRemoteDataSource,
    private val postsResultMapper: PostsResultMapper
) : PostsRepository {

    override fun getPosts(pageSize : Int): Single<List<Post>> {
        return postsRemoteDataSource.getPosts(pageSize).map { postsResultMapper.map(it) }
    }
}
