package com.jorgesanaguaray.qbit.detail.domain

import com.jorgesanaguaray.qbit.core.domain.Post

/**
 * Created by Jorge Sanaguaray
 */

interface DetailRepository {

    suspend fun getPostById(id: Int): Result<Post>

}