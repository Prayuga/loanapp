package com.yugs.loanapp.ui.loanlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yugs.core.ui.BaseActivity
import com.yugs.core.ui.ViewResource
import com.yugs.loanapp.R
import com.yugs.loanapp.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanListActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(
    ActivityHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: LoanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = LoanAdapter { loan ->
            println("zxc -> loan click $loan")
        }
        binding.rvLoan.adapter = adapter
        viewModel.fetchLoans()

    }

    override fun observeData() {
        viewModel.loanListData.observe(this) {
            when(it) {
                is ViewResource.Success -> {
                    adapter.submitList(it.data)
                }
                is ViewResource.Error -> {
                    showError(true, it.exception)
                }
                else -> { }
            }
        }
    }
}