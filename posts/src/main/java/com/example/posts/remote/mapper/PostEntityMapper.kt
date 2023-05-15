package com.example.posts.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.posts.data.model.PostEntity
import com.example.posts.remote.model.PostRemote
import javax.inject.Inject

class PostEntityMapper @Inject constructor() : Mapper<PostRemote, PostEntity> {

    override fun map(input: PostRemote): PostEntity {

        assertEssentialParams(input)

        return PostEntity(
            title = input.title!!,
            url = input.url!!,
            createdAt = input.published_date!!
        )
    }

    private fun assertEssentialParams(remote: PostRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.published_date, "published_date"),
            EssentialParam(remote.url, "url"),
            EssentialParam(remote.title, "title")
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
