package com.jorgesanaguaray.qbit.core.domain

/**
 * Created by Jorge Sanaguaray
 */

data class Post(

    val id: Int? = null,
    val title: String,
    val description: String,
    val category: String,
    val createdBy: String,
    val readingTime: String,
    val date: String,
    val thumbnailLink: String,
    val imageLink: String,
    val postLink: String

)