package com.jorgesanaguaray.qbit.home.domain

import com.jorgesanaguaray.qbit.core.domain.Post
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jorge Sanaguaray
 */

interface HomeRepository {

    fun getPosts(): Flow<List<Post>>

}