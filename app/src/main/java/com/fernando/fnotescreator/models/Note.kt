package com.fernando.fnotescreator.models

import java.time.LocalDate
import java.util.UUID

class Note {
    private val _id: String
    val id: String
        get() = _id

    private val _content: String
    val content: String
        get() = _content

    private val _name: String
    val name: String
        get() = _name

    private val _createdAt: LocalDate
    val createdAt: LocalDate
        get() = _createdAt

    constructor(id: String, content: String, createdAt: LocalDate, name: String) {
        this._id = id
        this._content = content
        this._createdAt = createdAt
        this._name = name
    }


    constructor(content: String, name: String) {
        this._id = UUID.randomUUID().toString()
        this._content = content
        this._createdAt = LocalDate.now()
        this._name = name
    }

    override fun toString(): String {
        return "PdfInfo(id=$id, content=$content, createdAt=$_createdAt)"
    }

}