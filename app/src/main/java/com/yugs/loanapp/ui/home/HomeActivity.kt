package com.yugs.loanapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.yugs.core.ui.BaseActivity
import com.yugs.core.ui.ViewResource
import com.yugs.loanapp.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(
    ActivityHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun initView() {
        binding.tvTitle.text = viewModel.myLabel
        viewModel.fetchLoans()
    }

    override fun observeData() {
        viewModel.loanListData.observe(this) {
            when(it) {
                is ViewResource.Success -> {
                    println("zxc -> success ${it.data}")
                }
                is ViewResource.Error -> {
                    println("zxc -> error ${it.exception?.message}")
                }
                else -> { println("zxc -> else") }
            }
        }
    }
}