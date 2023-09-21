package com.jorgesanaguaray.qbit.detail.domain.usecase

import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.detail.domain.DetailRepository

/**
 * Created by Jorge Sanaguaray
 */

class GetPostByIdUseCase(

    private val detailRepository: DetailRepository

) {

    suspend operator fun invoke(id: Int): Result<Post> {
        return detailRepository.getPostById(id)
    }

}