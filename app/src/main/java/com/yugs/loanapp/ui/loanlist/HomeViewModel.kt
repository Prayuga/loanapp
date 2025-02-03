package com.yugs.loanapp.ui.loanlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yugs.core.ui.ViewResource
import com.yugs.loanapp.domain.GetLoansUseCase
import com.yugs.loanapp.ui.viewparam.Loan
import kotlinx.coroutines.launch

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class HomeViewModel(
    val getLoansUseCase: GetLoansUseCase
): ViewModel() {

    val loanListData = MutableLiveData<ViewResource<List<Loan>>>()

    fun fetchLoans() {
        viewModelScope.launch {
            getLoansUseCase().collect {
                loanListData.value = it
            }
        }
    }
}