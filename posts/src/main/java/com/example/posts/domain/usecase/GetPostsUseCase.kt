package com.example.posts.domain.usecase

import com.example.core.scheduler.SchedulerProvider
import com.example.core.usecase.PaginationObservableUseCase
import com.example.posts.domain.model.Post
import com.example.posts.domain.repository.PostsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostsRepository,
    schedulerProvider: SchedulerProvider
) : PaginationObservableUseCase<Post, Void>(
    schedulerProvider.io(),
    schedulerProvider.ui()
) {

    override val pageSize: Int = 20

    override fun loadPage(params: Void?, page: Int, pageSize: Int): Observable<List<Post>> {
        return repository.getPosts(pageSize = pageSize).toObservable()
    }
}
