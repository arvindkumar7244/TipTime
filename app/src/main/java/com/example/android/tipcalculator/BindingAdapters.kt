package com.example.android.tipcalculator

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import kotlin.math.ceil

@BindingAdapter("cost", "serviceRate", "roundUpTip")
fun TextView.setTipAmount(cost: String?, serviceRate: ServiceRate, roundUpTip: Boolean) {
    var tipAmount: Double =
        if (cost == null || cost.toDoubleOrNull() == null) 0.0 else cost.toDouble()
    tipAmount = when (serviceRate) {
        ServiceRate.AMAZING -> tipAmount * 0.2
        ServiceRate.GOOD -> tipAmount * 0.18
        else -> tipAmount * 0.15
    }

    if (roundUpTip)
        tipAmount = ceil(tipAmount)
    val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
    this.text = resources.getString(R.string.tip_amount, formattedTip)
}

