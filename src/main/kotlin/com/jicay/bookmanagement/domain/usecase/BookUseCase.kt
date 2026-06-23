package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort

class BookUseCase(
    private val bookPort: BookPort
) {
    fun getAllBooks(): List<Book> {
        return bookPort.getAllBooks().sortedBy {
            it.name.lowercase()
        }
    }

    fun addBook(book: Book) {
        bookPort.createBook(book)
    }

    fun reserveBook(bookId: Long) {
        val book = bookPort.getBookById(bookId)
            ?: throw IllegalArgumentException("Book not found")

        if (book.reserved) {
            throw IllegalStateException("Book already reserved")
        }

        val updatedBook = book.copy(reserved = true)

        bookPort.updateBook(updatedBook)
    }


}