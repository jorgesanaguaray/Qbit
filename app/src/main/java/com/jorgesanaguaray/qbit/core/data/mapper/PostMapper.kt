package com.jorgesanaguaray.qbit.core.data.mapper

import com.jorgesanaguaray.qbit.core.data.local.PostEntity
import com.jorgesanaguaray.qbit.core.domain.Post

/**
 * Created by Jorge Sanaguaray
 */

fun Post.toDatabase(): PostEntity {

    return PostEntity(id, title, description, category, createdBy, thumbnailLink, imageLink, postLink)

}

fun PostEntity.toDomain(): Post {

    return Post(id, title, description, category, createdBy, thumbnailLink, imageLink, postLink)

}