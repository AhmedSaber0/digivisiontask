package com.example.posts.remote.api

import com.example.posts.remote.model.PostsResultRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("top-headlines")
    fun getPosts(@Query("pageSize") pageSize: Int): Single<PostsResultRemote>
}
