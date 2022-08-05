package com.example.android.tipcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

enum class ServiceRate {
    AMAZING,
    GOOD,
    OKAY
}

class TipViewModel : ViewModel() {
    var serviceRate = MutableLiveData(ServiceRate.AMAZING)
    var costString = MutableLiveData<String>()
    var roundUpTip = MutableLiveData(true)

    val amount = costString.combineWith(serviceRate) { cost, rate ->
        when (rate) {
            ServiceRate.AMAZING -> cost?.toDoubleOrNull()?.times(0.2) ?: 0.0
            ServiceRate.GOOD -> cost?.toDoubleOrNull()?.times(0.18) ?: 0.0
            else -> cost?.toDoubleOrNull()?.times(0.15) ?: 0.0
        }
    }.combineWith(roundUpTip) { amount, tip ->
        if (tip == true)
            amount?.let { ceil(it) }
        else
            amount
    }

    fun setServiceRate(serviceRate: ServiceRate) {
        this.serviceRate.value = serviceRate
    }

    private fun <T, K, R> LiveData<T>.combineWith(
        liveData: LiveData<K>,
        block: (T?, K?) -> R,
    ): LiveData<R> {
        val result = MediatorLiveData<R>()
        result.addSource(this) {
            result.value = block(this.value, liveData.value)
        }
        result.addSource(liveData) {
            result.value = block(this.value, liveData.value)
        }
        return result
    }

}