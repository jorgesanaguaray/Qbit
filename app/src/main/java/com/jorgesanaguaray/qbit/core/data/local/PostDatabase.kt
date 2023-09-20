package com.jorgesanaguaray.qbit.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Jorge Sanaguaray
 */

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract val postDao: PostDao

}