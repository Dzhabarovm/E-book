package com.example.ebook

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table")
    fun getAll(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE rating >= 4")
    fun getPopular(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE marked is 1")
    fun getFavorites(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE published_year == 2022")
    fun getNew(): LiveData<List<Book>>

    @Insert
    fun addBooks(books: List<Book>)

    @Query("UPDATE book_table set marked = not marked WHERE id = :id")
    fun updateMark(id: Long)

    @Query("SELECT * FROM book_table WHERE id = :id")
    fun getBook(id: Long): Book

//    @Query("SELECT * FROM book_table where title LIKE '%'  :string  '%' ")
//    fun getFoundRemainder(string: String):LiveData<List<Book>>
}