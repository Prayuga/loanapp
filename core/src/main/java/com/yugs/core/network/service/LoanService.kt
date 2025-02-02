package com.yugs.core.network.service

import com.yugs.core.network.response.LoanResponse
import retrofit2.http.GET

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
interface LoanService {

    @GET("andreascandle/p2p_json_test/main/api/json/loans.json")
    suspend fun getLoans(): List<LoanResponse>
}