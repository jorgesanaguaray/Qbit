package com.jorgesanaguaray.qbit.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val homeRepository: HomeRepository

) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getPosts()
    }

    private fun getPosts() {

        viewModelScope.launch {

            _isLoading.value = true

            homeRepository.getPosts().collectLatest {

                _posts.value = it
                _isLoading.value = false

            }

            _isLoading.value = false

        }

    }

}