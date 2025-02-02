package com.yugs.loanapp.di

import com.yugs.loanapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
object InjectionModules {

    fun getModules() = listOf(viewModels)

    val viewModels = module {
        viewModel { HomeViewModel() }
    }
}