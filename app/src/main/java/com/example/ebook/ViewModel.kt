package com.example.ebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val database = BookDatabase.getInstance(application.applicationContext)
    private val dao = database.bookDao

    val getAll: LiveData<List<Book>> = dao.getAll()
    val getPopular: LiveData<List<Book>> = dao.getPopular()
    val getNewBooks: LiveData<List<Book>> = dao.getNew()
    val getFavorites: LiveData<List<Book>> = dao.getFavorites()

    fun addBooks(books: List<Book>) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.addBooks(books)
        }
    }

    fun updateMark(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateMark(id)
        }
    }

    fun getBook(id: Long): Book {
        return dao.getBook(id)
    }

}