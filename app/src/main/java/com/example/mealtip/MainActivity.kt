package com.example.mealtip

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMealCost: EditText
    private lateinit var spinnerTipPercentage: Spinner
    private lateinit var buttonCalculate: Button
    private lateinit var textViewTipAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMealCost = findViewById(R.id.editTextMealCost)
        spinnerTipPercentage = findViewById(R.id.spinnerTipPercentage)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewTipAmount = findViewById(R.id.textViewTipAmount)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tip_percentages,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipPercentage.adapter = adapter

        buttonCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val mealCostString = editTextMealCost.text.toString()
        if (mealCostString.isEmpty()) {
            textViewTipAmount.text = "Please enter meal cost."
            return
        }

        val mealCost = mealCostString.toDouble()
        val percentageString = spinnerTipPercentage.selectedItem.toString()
        val percentage = percentageString.replace("%", "").toDouble() / 100.0

        val tipAmount = mealCost * percentage
        textViewTipAmount.text = String.format("Tip Amount: $%.2f", tipAmount)
    }
}