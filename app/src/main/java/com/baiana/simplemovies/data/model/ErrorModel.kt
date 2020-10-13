package com.baiana.simplemovies.data.model

data class ErrorModel(val code: Int, val message: String, val errorType: ErrorTypeEnum?) {
    constructor(code: Int, message: String) : this(code, message, getErrorTypeByCode(code))
}



