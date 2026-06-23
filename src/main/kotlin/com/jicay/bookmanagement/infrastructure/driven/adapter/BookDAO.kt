package com.jicay.bookmanagement.infrastructure.driven.adapter

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class BookDAO(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): BookPort {
    override fun getAllBooks(): List<Book> {
        return namedParameterJdbcTemplate
            .query("SELECT * FROM BOOK") { rs, _ ->
                Book(
                    id = rs.getLong("id"),
                    name = rs.getString("title"),
                    author = rs.getString("author"),
                    reserved = rs.getBoolean("reserved")
                )
            }
    }
    override fun createBook(book: Book) {
        namedParameterJdbcTemplate
            .update("INSERT INTO BOOK (title, author) values (:title, :author)", mapOf(
                "title" to book.name,
                "author" to book.author
            ))
    }

    override fun getBookById(id: Long): Book? {
        return namedParameterJdbcTemplate.query(
            "SELECT * FROM BOOK WHERE id = :id",
            mapOf("id" to id)
        ) { rs, _ ->
            Book(
                id = rs.getLong("id"),
                name = rs.getString("title"),
                author = rs.getString("author"),
                reserved = rs.getBoolean("reserved")
            )
        }.firstOrNull()
    }

    override fun updateBook(book: Book) {
        namedParameterJdbcTemplate.update(
            """
        UPDATE BOOK
        SET title = :title,
            author = :author,
            reserved = :reserved
        WHERE id = :id
        """.trimIndent(),
            mapOf(
                "id" to book.id,
                "title" to book.name,
                "author" to book.author,
                "reserved" to book.reserved
            )
        )
    }
}