package com.jorgesanaguaray.qbit.detail.data.di

import com.jorgesanaguaray.qbit.core.data.local.PostDao
import com.jorgesanaguaray.qbit.detail.data.DetailRepositoryImpl
import com.jorgesanaguaray.qbit.detail.domain.DetailRepository
import com.jorgesanaguaray.qbit.detail.domain.usecase.GetPostByIdUseCase
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
object DetailModule {

    @Singleton
    @Provides
    fun provideDetailRepository(postDao: PostDao): DetailRepository {
        return DetailRepositoryImpl(postDao)
    }

    @Provides
    @Singleton
    fun provideGetPostByIdUseCase(detailRepository: DetailRepository): GetPostByIdUseCase {
        return GetPostByIdUseCase(detailRepository)
    }

}