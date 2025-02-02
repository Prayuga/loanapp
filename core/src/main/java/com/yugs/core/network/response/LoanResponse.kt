package com.yugs.core.network.response

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
data class LoanResponse(
    val id: String?,
    val amount: Int?,
    val interestRate: Double?,
    val term: Int?,
    val purpose: String?,
    val riskRating: String?,
    val borrower: Borrower?,
    val collateral: Collateral?,
    val documents: List<Document>?,
    val repaymentSchedule: RepaymentSchedule?
)

data class Borrower(
    val id: String?,
    val name: String?,
    val email: String?,
    val creditScore: Int?
)

data class Collateral(
    val type: String?,
    val value: Int?
)

data class Document(
    val type: String?,
    val url: String?
)

data class RepaymentSchedule(
    val installments: List<Installment>?
)

data class Installment(
    val dueDate: String?,
    val amountDue: Int?
)