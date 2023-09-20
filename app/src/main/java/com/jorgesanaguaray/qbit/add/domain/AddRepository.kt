package com.jorgesanaguaray.qbit.add.domain

import com.jorgesanaguaray.qbit.core.domain.Post

/**
 * Created by Jorge Sanaguaray
 */

interface AddRepository {

    suspend fun insertPost(post: Post)

}