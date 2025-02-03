package com.yugs.loanapp.utils

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
fun <T> T.isNotNull(): Boolean {
    return this != null
}

fun <T> T.isNull(): Boolean {
    return this == null
}

