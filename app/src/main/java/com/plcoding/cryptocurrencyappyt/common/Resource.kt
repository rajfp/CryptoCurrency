package com.plcoding.cryptocurrencyappyt.common

sealed class Resource<T>(
    val isSuccess: Boolean,
    val data: T? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T) : Resource<T>(isSuccess = true, data = data)
    class Failure<T>(message: String,data: T? = null) : Resource<T>(isSuccess = false, errorMsg = message)
    class Loading<T>(data: T? = null) : Resource<T>(isSuccess = false, data)
}
