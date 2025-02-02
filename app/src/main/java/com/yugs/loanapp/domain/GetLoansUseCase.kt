package com.yugs.loanapp.domain

import com.yugs.core.data.DataResource
import com.yugs.core.domain.BaseUseCase
import com.yugs.core.ui.ViewResource
import com.yugs.loanapp.data.repository.LoanRepository
import com.yugs.loanapp.ui.viewparam.Loan
import com.yugs.loanapp.ui.viewparam.mapToViewParam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class GetLoansUseCase(
    private val repository: LoanRepository,
    val dispatcher: CoroutineDispatcher
) : BaseUseCase<Any, List<Loan>>(dispatcher) {
    override suspend fun execute(param: Any?): Flow<ViewResource<List<Loan>>> {
        return repository.getLoans().map {
            when (it) {
                is DataResource.Success -> ViewResource.Success(it.data.mapToViewParam())
                is DataResource.Error -> ViewResource.Error(it.exception)
            }
        }
    }

}