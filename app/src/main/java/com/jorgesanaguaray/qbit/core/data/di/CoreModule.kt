package com.jorgesanaguaray.qbit.core.data.di

import android.app.Application
import androidx.room.Room
import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.core.data.local.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jorge Sanaguaray
 */

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun providePostDatabase(application: Application): PostDatabase {
        return Room.databaseBuilder(application, PostDatabase::class.java, "PostDatabase.db").build()
    }

    @Provides
    @Singleton
    fun providePostDao(postDatabase: PostDatabase): PostDao {
        return postDatabase.postDao
    }

}