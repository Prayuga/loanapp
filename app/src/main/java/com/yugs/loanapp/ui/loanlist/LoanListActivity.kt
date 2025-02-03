package com.yugs.loanapp.ui.loanlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yugs.core.ui.BaseActivity
import com.yugs.core.ui.ViewResource
import com.yugs.loanapp.R
import com.yugs.loanapp.databinding.ActivityHomeBinding
import com.yugs.loanapp.ui.loandetails.LoanDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanListActivity : BaseActivity<ActivityHomeBinding, LoanListViewModel>(
    ActivityHomeBinding::inflate
) {
    override val viewModel: LoanListViewModel by viewModel()
    private lateinit var adapter: LoanAdapter
    private var isAscending = true

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
            val intent = Intent(this, LoanDetailsActivity::class.java)
            intent.putExtra("loan_data", loan)
            startActivity(intent)
        }
        binding.rvLoan.adapter = adapter
        binding.btnSort.setOnClickListener {
            adapter.sortListByTerm(isAscending)
            Toast.makeText(this, "Sort by term ${if (isAscending) "ascending" else "descending"}", Toast.LENGTH_SHORT).show()
            isAscending = !isAscending
            binding.rvLoan.postDelayed({ binding.rvLoan.scrollToPosition(0) }, 100)
        }
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