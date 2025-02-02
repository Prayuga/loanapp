package com.yugs.core.data

import java.lang.Exception

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
sealed class DataResource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : DataResource<T>(data, exception = exception)
}
