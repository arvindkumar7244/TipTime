package com.example.android.tipcalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ServiceRate {
    AMAZING,
    GOOD,
    OKAY
}

class TipViewModel : ViewModel() {
    var serviceRate = MutableLiveData(ServiceRate.AMAZING)
    var costString = MutableLiveData<String>()
    var roundUpTip = MutableLiveData(false)

    fun setServiceRate(serviceRate: ServiceRate) {
        this.serviceRate.value = serviceRate
    }

}