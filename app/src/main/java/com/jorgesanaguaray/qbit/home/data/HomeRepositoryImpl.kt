package com.jorgesanaguaray.qbit.home.data

import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.core.data.mapper.toDomain
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Jorge Sanaguaray
 */

class HomeRepositoryImpl(

    private val postDao: PostDao

) : HomeRepository {

    override fun getPosts(): Flow<List<Post>> {

        return postDao.getPosts().map { postsEntity ->

            postsEntity.map {

                it.toDomain()

            }

        }

    }

}