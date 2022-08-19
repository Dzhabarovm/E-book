package com.example.ebook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var cover: Int? = null,
    var title: String = "",
    var author: String = "",
    var published_year: Int = 0,
    var marked: Boolean = false,
    var rating: Int = 0,
)
