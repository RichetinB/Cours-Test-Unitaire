package com.jicay.bookmanagement.infrastructure.driving.web.dto

import com.jicay.bookmanagement.domain.model.Book

data class BookDTO(
    val id: Long?,
    val name: String,
    val author: String,
    val reserved: Boolean
) {
    fun toDomain(): Book {
        return Book(
            id = id,
            name = name,
            author = author,
            reserved = reserved
        )
    }
}

fun Book.toDto() = BookDTO(
    id = id,
    name = name,
    author = author,
    reserved = reserved
)