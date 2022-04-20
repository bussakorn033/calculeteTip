package com.example.caltip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.caltip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* on click button calculete */
        binding.calculateButton.setOnClickListener { calculateTip() }

        /* on enter keyboard */
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }

    }

    /* hidden keyboard */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    /* handler calculete tip */
    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
//            binding.tipResult.text = ""
            displayTip(0.0)
            return
        }
//        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (/*selectedId*/binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
//        val roundUp = binding.roundUpSwitch.isChecked
        if (/*roundUp*/binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
//        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        displayTip(tip)
    }


    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }



}