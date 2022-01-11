package com.example.mercedesnews.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(

    tableName = "news",

    )
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val title: String,
    val url: String,
    val urlToImage: String
): Serializable