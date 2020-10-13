package com.baiana.simplemovies.data.model

enum class ErrorTypeEnum(val codeRange: IntRange){
    SUCCESS(200..299),
    CLIENT_ERROR(400..499),
    SERVER_ERROR(500..599)
}

fun getErrorTypeByCode(code: Int) =
    when (code) {
        in ErrorTypeEnum.SUCCESS.codeRange -> { ErrorTypeEnum.SUCCESS }
        in ErrorTypeEnum.CLIENT_ERROR.codeRange -> { ErrorTypeEnum.CLIENT_ERROR }
        in ErrorTypeEnum.SERVER_ERROR.codeRange -> { ErrorTypeEnum.SERVER_ERROR }
        else -> null
    }
