package com.example.posts.presentation.posts.viewmodel

import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.usecase.UseCaseResult
import com.example.core.util.NetworkChecker
import com.example.posts.domain.model.Post
import com.example.posts.domain.usecase.GetPostsUseCase
import com.example.posts.presentation.posts.mapper.PostUiMapper
import com.example.posts.presentation.posts.viewstate.PostsCoordinatorEvent
import com.example.posts.presentation.posts.viewstate.PostsViewAction
import com.example.posts.presentation.posts.viewstate.PostsViewEvent
import com.example.posts.presentation.posts.viewstate.PostsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postUiMapper: PostUiMapper,
    private val networkChecker: NetworkChecker
) : BaseViewModel<PostsViewState, PostsViewEvent, PostsViewAction, PostsCoordinatorEvent>() {

    private val compositeDisposable = CompositeDisposable()

    override val initViewState: PostsViewState = PostsViewState.Loading.also { updateViewState(it) }

    override fun postAction(action: PostsViewAction) {
        when (action) {
            is PostsViewAction.LoadNextPage -> loadNextPage()

            is PostsViewAction.OpenPostDetails -> {
                sendCoordinatorEvent(PostsCoordinatorEvent.OpenDetails(action.title, action.url))
            }
        }
    }

    init {
        safeRequest {
            getPostsUseCase.execute().subscribeWith(BuyersSubscriber())
                .also { compositeDisposable::add }
        }
    }

    private inner class BuyersSubscriber : DisposableObserver<UseCaseResult<Post>>() {

        private fun updateError(e: Throwable) {
            val success = currentViewState() as? PostsViewState.Success
            if (success != null) {
                updateViewEvent(PostsViewEvent.NextPageError)
                updateViewState(success.copy(loadingNextPage = false))
            } else {
                updateViewState(PostsViewState.Error)
            }
            updateViewState(PostsViewState.Error)
        }

        override fun onNext(t: UseCaseResult<Post>) {
            when (t) {
                is UseCaseResult.Success -> {
                    updateViewState(
                        PostsViewState.Success(
                            posts = t.data.map { post -> postUiMapper.map(post) },
                            loadingNextPage = false,
                            empty = t.data.isEmpty()
                        )
                    )
                }

                is UseCaseResult.Error -> updateError(t.throwable)
            }
        }

        override fun onError(e: Throwable) {
            updateError(e)
        }

        override fun onComplete() {
        }
    }

    private fun loadNextPage() {
        val successState = currentViewState() as? PostsViewState.Success
        val isLoadNext = successState?.loadingNextPage ?: false
        if (successState != null && !isLoadNext && getPostsUseCase.hasMore) {
            safeRequest(
                request = {
                    getPostsUseCase.nextPage()
                    updateViewState(successState.copy(loadingNextPage = true))
                }
            )
        }
    }

    private fun safeRequest(request: () -> Unit) {
        if (networkChecker.isConnectedToInternet()) {
            request.invoke()
        } else {
            updateViewEvent(PostsViewEvent.NoInternet)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
