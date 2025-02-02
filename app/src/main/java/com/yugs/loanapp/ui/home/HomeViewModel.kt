package com.yugs.loanapp.ui.home

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
    val myLabel = "OKEEE"

    val loanListData = MutableLiveData<ViewResource<List<Loan>>>()

    fun fetchLoans() {
        viewModelScope.launch {
            println("zxc -> fetch Loans")
            getLoansUseCase().collect {
                println("zxc -> collect ${it.data}")
                loanListData.value = it
            }
        }
    }
}