package com.yugs.loanapp.di

import com.yugs.core.network.networkModule
import com.yugs.loanapp.data.network.datasource.LoanNetworkDataSource
import com.yugs.loanapp.data.network.datasource.LoanNetworkDataSourceImpl
import com.yugs.loanapp.data.repository.LoanRepository
import com.yugs.loanapp.data.repository.LoanRepositoryImpl
import com.yugs.loanapp.domain.GetLoansUseCase
import com.yugs.loanapp.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
object InjectionModules {

    fun getModules() = listOf(networkModule, dataSource, repository, useCases, viewModels)

    private val dataSource = module {
        single<LoanNetworkDataSource> { LoanNetworkDataSourceImpl(get()) }
    }

    private val repository = module {
        single<LoanRepository> { LoanRepositoryImpl(get()) }
    }

    private val useCases = module {
        single { GetLoansUseCase(get(), dispatcher = Dispatchers.IO) }
    }

    private val viewModels = module {
        viewModel { HomeViewModel(get()) }
    }
}