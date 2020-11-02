package com.ch8n.thatsmine.base.utils

sealed class Result<out E, out V> {

    data class Success<out V>(val value: V) : Result<Nothing, V>()
    data class Error<out E>(val error: E) : Result<E, Nothing>()

    companion object Factory {
        inline fun <V> build(function: () -> V): Result<Exception, V> =
            try {
                Success(function.invoke())
            } catch (e: kotlin.Exception) {
                Error(e)
            }
    }

}