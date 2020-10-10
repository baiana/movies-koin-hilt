package com.baiana.simplemovies.network

sealed class CallResponse<out T> {
    class Success<out T>(val result: T) : CallResponse<T>()
    class Failure(val throwable: Throwable) : CallResponse<Nothing>()
}