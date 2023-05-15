package com.example.posts.data.mapper

import com.example.core.mapper.Mapper
import com.example.posts.data.model.PostsResultEntity
import com.example.posts.domain.model.Post
import javax.inject.Inject

class PostsResultMapper @Inject constructor(
    private val postMapper: PostMapper
) : Mapper<PostsResultEntity, List<Post>> {

    override fun map(input: PostsResultEntity): List<Post> {
        return postMapper.mapList(input.postsEntities)
    }
}
