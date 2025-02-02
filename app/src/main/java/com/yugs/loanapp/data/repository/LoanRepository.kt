package com.yugs.loanapp.data.repository

import com.yugs.core.data.BaseRepositoryImpl
import com.yugs.core.data.DataResource
import com.yugs.core.ui.BaseContract
import com.yugs.loanapp.data.network.datasource.LoanNetworkDataSource
import com.yugs.core.network.response.LoanResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanRepositoryImpl(private val dataSource: LoanNetworkDataSource) : BaseRepositoryImpl(), LoanRepository {
    override suspend fun getLoans(): Flow<DataResource<List<LoanResponse>>> =
        flow {
            emit(safeNetworkCall { dataSource.getLoans() })
        }

}

interface LoanRepository: BaseContract.BaseRepository {
    suspend fun getLoans(): Flow<DataResource<List<LoanResponse>>>
}