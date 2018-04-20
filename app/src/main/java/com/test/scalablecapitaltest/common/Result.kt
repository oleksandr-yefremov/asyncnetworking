package com.test.scalablecapitaltest.common

//sealed class Result<out T : Any>
//
//data class Success<out T : Any>(val data: T) : Result<T>()
//
//data class Failure(val error: Throwable?) : Result<Nothing>()

interface Result<in T> {
    fun onSuccess(data: T)
    fun onFailure(error: Throwable)
}