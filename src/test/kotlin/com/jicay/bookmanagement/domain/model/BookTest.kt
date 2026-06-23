package com.jicay.bookmanagement.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class BookTest : FunSpec({

    test("name should not be empty") {
        shouldThrow<IllegalArgumentException> {
            Book(name = "", author = "Victor Hugo")
        }
    }

    test("author should not be empty") {
        shouldThrow<IllegalArgumentException> {
            Book(name = "Les Misérables", author = "")
        }
    }

})