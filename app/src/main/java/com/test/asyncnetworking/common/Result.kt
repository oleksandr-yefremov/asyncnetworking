package com.test.asyncnetworking.common

/**
 * Wrapper for async callbacks which deliver either data or exception
 */
interface Result<in T> {
    fun onSuccess(data: T)
    fun onFailure(error: Throwable)
}

/**
 * Same as [Result] but with argument indicating whether data is coming from cache
 */
interface CachedResult<in T> {
    fun onSuccess(data: T, isCache: Boolean)
    fun onFailure(error: Throwable)
}