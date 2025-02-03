package com.yugs.loanapp.ui.loanlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yugs.loanapp.databinding.ItemLoanBinding
import com.yugs.loanapp.ui.viewparam.Loan
import com.yugs.loanapp.utils.toSpannableHighlight

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
class LoanAdapter(
    private val onLoanClick: (Loan) -> Unit
) : ListAdapter<Loan, LoanAdapter.LoanViewHolder>(LoanDiffCallback()) {

    inner class LoanViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(loan: Loan) {
            binding.data = loan
            binding.tvInterest.text = "${loan.interestPercentage}% interest".toSpannableHighlight()
            binding.tvTerm.text = "${loan.term} months".toSpannableHighlight()
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

    fun sortListByTerm(ascending: Boolean) {
        val sortedList = if (ascending) {
            currentList.sortedBy { it.term }
        } else {
            currentList.sortedByDescending { it.term }
        }
        submitList(sortedList) // Update RecyclerView
    }
}

class LoanDiffCallback : DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean = oldItem == newItem
}

