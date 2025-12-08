package com.fernando.editordepdf.models

import com.fernando.editordepdf.enums.PdfState
import java.time.LocalDate
import java.util.UUID

class PdfInfo {
    private val _id: String
    val id: String
        get() = _id

    private var _content: String
    val content: String
        get() = _content

    private val _createdAt: LocalDate
    val createdAt: LocalDate
        get() = _createdAt

    private val _isReadOnly: Boolean
    val isReadOnly: Boolean
        get() = _isReadOnly

    private var _state: PdfState
    val state: PdfState
        get() = _state

    constructor(id: String,
                        content: String, createdAt: LocalDate,
                        isReadOnly: Boolean, state: PdfState) {
        this._id = id
        this._content = content
        this._createdAt = createdAt
        this._isReadOnly = isReadOnly
        this._state = state
    }

    constructor(content: String, createdAt: LocalDate,
                        isReadOnly: Boolean) {
        this._id = UUID.randomUUID().toString()
        this._content = content
        this._createdAt = createdAt
        this._isReadOnly = isReadOnly
        this._state = PdfState.NOT_SAVED
    }

    fun updateToSaved() {
        this._state = PdfState.SAVED
    }

    fun updateToNotSaved() {
        this._state = PdfState.NOT_SAVED
    }

}