package com.yugs.loanapp

import android.app.Application
import com.yugs.loanapp.di.InjectionModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LoanApplication)
            modules(InjectionModules.getModules())
        }
    }
}