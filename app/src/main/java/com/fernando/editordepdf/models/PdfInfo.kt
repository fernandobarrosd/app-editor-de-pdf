package com.fernando.editordepdf.models

import android.os.Build
import androidx.annotation.RequiresApi
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

    private var _name: String
    val name: String
        get() = _name

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
                        isReadOnly: Boolean, state: PdfState,
        name: String) {
        this._id = id
        this._content = content
        this._createdAt = createdAt
        this._isReadOnly = isReadOnly
        this._state = state
        this._name = name
    }

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(content: String, isReadOnly: Boolean, name: String) {
        this._id = UUID.randomUUID().toString()
        this._content = content
        this._createdAt = LocalDate.now()
        this._isReadOnly = isReadOnly
        this._state = PdfState.NOT_SAVED
        this._name = name
    }

    fun updateToSaved() {
        this._state = PdfState.SAVED
    }

    override fun toString(): String {
        return "PdfInfo(id=$id, content=$content, createdAt=$_createdAt, isReadOnly=$isReadOnly, state=$state)"
    }

}