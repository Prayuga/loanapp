package com.yugs.loanapp.ui.loanlist

import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yugs.loanapp.databinding.ItemLoanBinding
import com.yugs.loanapp.ui.viewparam.Loan

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanAdapter(
    private val onLoanClick: (Loan) -> Unit
) : ListAdapter<Loan, LoanAdapter.LoanViewHolder>(LoanDiffCallback()) {

    inner class LoanViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loan: Loan) {
            binding.data = loan
            binding.tvInterest.text = adjustTextSize("${loan.interestPercentage}% interest")
            binding.tvTerm.text = adjustTextSize("${loan.term} months")
            binding.root.setOnClickListener { onLoanClick(loan) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LoanDiffCallback : DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean = oldItem == newItem
}

private fun adjustTextSize(text: String): SpannableString {
    val spannable = SpannableString(text)
    spannable.setSpan(RelativeSizeSpan(1.5f), 0, text.indexOf(" "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannable
}