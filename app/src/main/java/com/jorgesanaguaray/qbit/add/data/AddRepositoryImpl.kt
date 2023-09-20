package com.jorgesanaguaray.qbit.add.data

import com.jorgesanaguaray.qbit.add.domain.AddRepository
import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.core.data.mapper.toDatabase
import com.jorgesanaguaray.qbit.core.domain.Post

/**
 * Created by Jorge Sanaguaray
 */

class AddRepositoryImpl(

    private val postDao: PostDao

) : AddRepository {

    override suspend fun insertPost(post: Post) {

        postDao.insertPost(post.toDatabase())

    }

}