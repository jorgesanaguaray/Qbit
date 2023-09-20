package com.jorgesanaguaray.qbit.add.data.di

import com.jorgesanaguaray.qbit.add.data.AddRepositoryImpl
import com.jorgesanaguaray.qbit.add.domain.AddRepository
import com.jorgesanaguaray.qbit.core.data.local.PostDao
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
object AddModule {

    @Provides
    @Singleton
    fun provideAddRepository(postDao: PostDao): AddRepository {
        return AddRepositoryImpl(postDao)
    }

}