package com.example.android.tipcalculator

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.tipcalculator.databinding.ActivityMainBinding
import kotlin.math.log

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

        Log.d("TAG", "Mandeep Singh")
      //  Log.d("TAG", "onCreate: Hi There")

    }

    private fun proceed() {
        val message = "Payable amount is ${viewModel.costString.value} + ${binding.tipResult.text} "
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}