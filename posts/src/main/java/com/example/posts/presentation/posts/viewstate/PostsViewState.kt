package com.example.posts.presentation.posts.viewstate

import com.example.core.viewstate.ViewState
import com.example.posts.presentation.posts.model.PostUiModel

sealed class PostsViewState : ViewState {

    object Loading : PostsViewState()

    data class Success(
        val posts: List<PostUiModel>,
        val loadingNextPage: Boolean,
        val empty: Boolean,
    ) : PostsViewState()

    object Error : PostsViewState()
}
