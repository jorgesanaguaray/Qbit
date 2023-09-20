package com.jorgesanaguaray.qbit.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jorge Sanaguaray
 */

@Entity(tableName = "post_table")
data class PostEntity(

    @PrimaryKey(autoGenerate = true)
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