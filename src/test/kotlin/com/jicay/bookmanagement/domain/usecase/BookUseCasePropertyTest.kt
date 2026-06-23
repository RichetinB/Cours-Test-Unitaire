package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll

class InMemoryBookPort : BookPort {

    private val books = mutableListOf<Book>()
    private var idSequence = 1L

    override fun getAllBooks(): List<Book> = books

    override fun createBook(book: Book) {
        books.add(
            book.copy(id = idSequence++)
        )
    }

    override fun getBookById(id: Long): Book? {
        return books.find { it.id == id }
    }

    override fun updateBook(book: Book) {
        val index = books.indexOfFirst { it.id == book.id }
        if (index != -1) {
            books[index] = book
        }
    }

    fun clear() {
        books.clear()
        idSequence = 1L
    }
}