package com.baiana.simplemovies.data.model

sealed class CallResponse<out T> {
    class Success<out T>(val result: T) : CallResponse<T>()
    class Failure(val error: ErrorModel) : CallResponse<Nothing>()
}