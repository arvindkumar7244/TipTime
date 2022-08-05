package com.example.android.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TipViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.calculateButton.setOnClickListener { proceed() }
    }

    private fun proceed() {
        val payableAmount: Double =
            viewModel.amount.value?.let { viewModel.costString.value?.toDoubleOrNull()?.plus(it) }
                ?: 0.0
        val message =
            "Payable amount is ${NumberFormat.getCurrencyInstance().format(payableAmount)}"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}