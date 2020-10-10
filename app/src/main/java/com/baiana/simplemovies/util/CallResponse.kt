package com.baiana.simplemovies.util

sealed class CallResponse<out T> {
    class Success<out T>(val result: T) : CallResponse<T>()
    class Failure(val error: String) : CallResponse<Nothing>()
}