package com.yugs.core.network.exception

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class ApiErrorException(override val message: String? = null, val httpCode: Int? = null) : Exception() {
}