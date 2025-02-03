package com.yugs.loanapp.utils

import android.app.AlertDialog
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @Author: Prayuga
 * @Date: 2/2/2025
 */
fun <T> T.isNotNull(): Boolean {
    return this != null
}

fun <T> T.isNull(): Boolean {
    return this == null
}

fun String.toSpannableHighlight(): SpannableString {
    val spannable = SpannableString(this)
    val spaceIndex = this.indexOf(" ")
    if (spaceIndex != -1) {
        spannable.setSpan(RelativeSizeSpan(1.5f), 0, spaceIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return spannable
}

fun String.toFormattedDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(this) ?: return this
        outputFormat.format(date)
    } catch (e: Exception) {
        this
    }
}

fun Context.showImageDialog(imageUrl: String) {
    val imageView = ImageView(this)

    Glide.with(this)
        .load(imageUrl)
        .into(imageView)

    AlertDialog.Builder(this)
        .setView(imageView)
        .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
        .show()
}