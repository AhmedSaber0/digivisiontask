package com.example.posts.presentation.posts.viewstate

import com.example.core.navigation.CoordinatorEvent

sealed class PostsCoordinatorEvent : CoordinatorEvent {

    data class OpenDetails(val title: String, val url: String) : PostsCoordinatorEvent()
}
