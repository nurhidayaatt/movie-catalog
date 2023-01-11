package com.nurhidayaatt.core.data.source.remote.network

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResponse<T>(data, message)
    class Empty<T>(data: T? = null) : ApiResponse<T>(data)
}