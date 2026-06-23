package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.kotest.assertions.throwables.shouldThrow

class BookUseCaseTest : FunSpec({

    val bookPort = mockk<BookPort>()
    val bookUseCase = BookUseCase(bookPort)

    test("get all books should returns all books sorted by name") {
        every { bookPort.getAllBooks() } returns listOf(
            Book(name = "Les Misérables", author = "Victor Hugo"),
            Book(name = "Hamlet", author = "William Shakespeare")
        )

        val res = bookUseCase.getAllBooks()

        res.shouldContainExactly(
            Book(name = "Hamlet", author = "William Shakespeare"),
            Book(name = "Les Misérables", author = "Victor Hugo")
        )
    }

    test("add book") {
        justRun { bookPort.createBook(any()) }

        val book = Book(name = "Les Misérables", author = "Victor Hugo")
        bookUseCase.addBook(book)

        verify(exactly = 1) { bookPort.createBook(book) }
    }

    test("reserve book successfully") {
        val book = Book(id = 1, name = "Test", author = "Author", reserved = false)

        every { bookPort.getBookById(1) } returns book
        justRun { bookPort.updateBook(any()) }

        bookUseCase.reserveBook(1)

        verify {
            bookPort.updateBook(book.copy(reserved = true))
        }
    }

    test("reserve book should fail when book not found") {
        every { bookPort.getBookById(1) } returns null

        shouldThrow<IllegalArgumentException> {
            bookUseCase.reserveBook(1)
        }
    }

    test("reserve book should fail when already reserved") {
        val book = Book(id = 1, name = "Test", author = "Author", reserved = true)

        every { bookPort.getBookById(1) } returns book

        shouldThrow<IllegalStateException> {
            bookUseCase.reserveBook(1)
        }
    }

})