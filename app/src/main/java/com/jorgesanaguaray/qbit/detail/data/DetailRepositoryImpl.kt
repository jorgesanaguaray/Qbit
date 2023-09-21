package com.jorgesanaguaray.qbit.detail.data

import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.core.data.mapper.toDomain
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.detail.domain.DetailRepository

/**
 * Created by Jorge Sanaguaray
 */

class DetailRepositoryImpl(

    private val postDao: PostDao

) : DetailRepository {

    override suspend fun getPostById(id: Int): Result<Post> {

        return try {

            Result.success(postDao.getPostById(id).toDomain())

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}