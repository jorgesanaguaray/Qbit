package com.jorgesanaguaray.qbit.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jorge Sanaguaray
 */

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Query("SELECT * FROM post_table ORDER BY id DESC")
    fun getPosts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM post_table WHERE id = :id")
    suspend fun getPostById(id: Int): PostEntity

}