package com.fernando.editordepdf.mappers

import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.room.entities.PdfInfoEntity


fun PdfInfo.toRoomEntity() : PdfInfoEntity {
    return PdfInfoEntity(
        id = id,
        content = content,
        createdAt = createdAt,
        isReadOnly = isReadOnly,
        state = state,
        name = name
    )
}

fun PdfInfoEntity.toEntity() : PdfInfo {
    return PdfInfo(
        id = id,
        content = content,
        createdAt = createdAt,
        isReadOnly = isReadOnly,
        state = state,
        name = name
    )
}