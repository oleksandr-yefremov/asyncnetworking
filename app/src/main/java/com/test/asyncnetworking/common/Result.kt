package com.test.asyncnetworking.common

interface Result<in T> {
    fun onSuccess(data: T)
    fun onFailure(error: Throwable)
}