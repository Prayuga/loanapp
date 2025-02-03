package com.yugs.loanapp.ui.loandetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yugs.core.ui.BaseActivity
import com.yugs.loanapp.R
import com.yugs.loanapp.databinding.ActivityLoanDetailsBinding
import com.yugs.loanapp.ui.viewparam.Loan
import com.yugs.loanapp.utils.showImageDialog
import com.yugs.loanapp.utils.toFormattedDate
import com.yugs.loanapp.utils.toSpannableHighlight
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanDetailsActivity : BaseActivity<ActivityLoanDetailsBinding, LoanDetailsViewModel>(
    ActivityLoanDetailsBinding::inflate
) {
    override val viewModel: LoanDetailsViewModel by viewModel()

    @SuppressLint("SetTextI18n")
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
        val loan: Loan? = intent.getParcelableExtra("loan_data")
        binding.btnBack.setOnClickListener { finish() }
        binding.data = loan
        binding.tvInterest.text = "${loan?.interestPercentage}% interest".toSpannableHighlight()
        binding.tvTerm.text = "${loan?.term} months".toSpannableHighlight()
        binding.tvCollateral.text = "${loan?.collateral?.type} : $${loan?.collateral?.value}"
        val textRepayment = loan?.repaymentSchedule?.installments
            ?.joinToString("\n") { "- ${it.dueDate.toFormattedDate()} : $${it.amountDue}" } ?: "No repayment schedule"

        binding.tvRepayment.text = textRepayment
        binding.btnDocument.setOnClickListener {
            loan?.documents?.firstOrNull()?.url?.let { imageUrl ->
                showImageDialog("https://raw.githubusercontent.com/andreascandle/p2p_json_test/main${imageUrl}")
            }
        }
    }
}