package com.yugs.loanapp.ui.viewparam

import android.os.Parcelable
import com.yugs.core.network.response.LoanResponse
import kotlinx.parcelize.Parcelize

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
@Parcelize
data class Loan(
    val id: String,
    val amount: Int,
    val interestRate: Double,
    val interestPercentage: String,
    val term: Int,
    val purpose: String,
    val riskRating: String,
    val borrower: Borrower,
    val collateral: Collateral,
    val documents: List<Document>,
    val repaymentSchedule: RepaymentSchedule
) : Parcelable

@Parcelize
data class Borrower(
    val id: String,
    val name: String,
    val email: String,
    val creditScore: Int
): Parcelable

@Parcelize
data class Collateral(
    val type: String,
    val value: Int
): Parcelable

@Parcelize
data class Document(
    val type: String,
    val url: String
): Parcelable

@Parcelize
data class RepaymentSchedule(
    val installments: List<Installment>
): Parcelable

@Parcelize
data class Installment(
    val dueDate: String,
    val amountDue: Int
): Parcelable

fun List<LoanResponse>?.mapToViewParam() = mutableListOf<Loan>().apply {
    addAll(this@mapToViewParam?.map {
        it.mapToViewParam()
    } ?: listOf())
}

fun LoanResponse?.mapToViewParam(): Loan {
    return Loan(
        id = this?.id.orEmpty(),
        amount = this?.amount ?: 0,
        interestRate = this?.interestRate ?: 0.0,
        interestPercentage = "${(this?.interestRate?.times(100))?.toInt()}",
        term = this?.term ?: 0,
        purpose = this?.purpose.orEmpty(),
        riskRating = this?.riskRating.orEmpty(),
        borrower = Borrower(
            id = this?.borrower?.id.orEmpty(),
            name = this?.borrower?.name.orEmpty(),
            email = this?.borrower?.email.orEmpty(),
            creditScore = this?.borrower?.creditScore ?: 0
        ),
        collateral = Collateral(
            type = this?.collateral?.type.orEmpty(),
            value = this?.collateral?.value ?: 0
        ),
        documents = this?.documents?.map {
            Document(
                type = it.type.orEmpty(),
                url = it.url.orEmpty()
            )
        } ?: emptyList(),
        repaymentSchedule = RepaymentSchedule(
            installments = this?.repaymentSchedule?.installments?.map {
                Installment(
                    dueDate = it.dueDate.orEmpty(),
                    amountDue = it.amountDue ?: 0
                )
            } ?: emptyList()
        )
    )
}