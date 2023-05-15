package com.example.posts.presentation.posts.viewstate

import com.example.core.viewstate.ViewAction

sealed class PostsViewAction : ViewAction {

    object LoadNextPage : PostsViewAction()

    data class OpenPostDetails(val title: String, val url: String) : PostsViewAction()
}
