package com.jorgesanaguaray.qbit.home.data.di

import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.home.data.HomeRepositoryImpl
import com.jorgesanaguaray.qbit.home.domain.HomeRepository
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
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(postDao: PostDao): HomeRepository {
        return HomeRepositoryImpl(postDao)
    }

}