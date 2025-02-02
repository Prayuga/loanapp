package com.yugs.loanapp.data.network.datasource

import com.yugs.core.network.service.LoanService
import com.yugs.core.network.response.LoanResponse

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanNetworkDataSourceImpl(val apiService: LoanService) : LoanNetworkDataSource {
    override suspend fun getLoans(): List<LoanResponse> = apiService.getLoans()
}

interface LoanNetworkDataSource {
    suspend fun getLoans(): List<LoanResponse>
}