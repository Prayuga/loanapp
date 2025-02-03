package com.yugs.loanapp.data.network.datasource

import com.yugs.core.network.response.LoanResponse
import com.yugs.core.network.service.LoanService

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanNetworkDataSourceImpl(private val apiService: LoanService) : LoanNetworkDataSource {
    override suspend fun getLoans(): List<LoanResponse> = apiService.getLoans()
}

interface LoanNetworkDataSource {
    suspend fun getLoans(): List<LoanResponse>
}