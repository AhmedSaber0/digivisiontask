package com.example.posts.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.posts.data.model.PostsResultEntity
import com.example.posts.remote.model.PostsResultRemote
import javax.inject.Inject

class PostsEntityMapper @Inject constructor(
    private val postEntityMapper: PostEntityMapper
) : Mapper<PostsResultRemote, PostsResultEntity> {

    override fun map(input: PostsResultRemote): PostsResultEntity {

        assertEssentialParams(input)

        return PostsResultEntity(
            postsEntities = postEntityMapper.mapList(input.postsRemotes!!),
        )
    }

    private fun assertEssentialParams(remote: PostsResultRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.totalResults, "totalResults"),
            EssentialParam(remote.postsRemotes, "articles"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
