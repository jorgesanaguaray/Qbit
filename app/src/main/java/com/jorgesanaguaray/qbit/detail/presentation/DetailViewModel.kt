package com.jorgesanaguaray.qbit.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.detail.domain.usecase.GetPostByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

@HiltViewModel
class DetailViewModel @Inject constructor(

    private val getPostByIdUseCase: GetPostByIdUseCase

) : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> get() = _post

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getPostById(id: Int) {

        viewModelScope.launch {

            _isLoading.value = true

            getPostByIdUseCase(id).onSuccess {

                _post.value = it

            }.onFailure {}

            _isLoading.value = false

        }

    }

}