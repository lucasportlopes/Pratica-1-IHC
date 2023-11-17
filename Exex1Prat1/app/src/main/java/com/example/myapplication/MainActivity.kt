package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var buttonSum: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById<EditText>(R.id.nSumOne)
        editText2 = findViewById<EditText>(R.id.nSumTwo)
        buttonSum = findViewById<Button>(R.id.buttonSum)
        textViewResult = findViewById<TextView>(R.id.nResultSum)

        buttonSum.setOnClickListener { sumNumbers() }
    }

    private fun sumNumbers() {
        val number1 = editText1.text.toString().toDoubleOrNull() ?: 0.0
        val number2 = editText2.text.toString().toDoubleOrNull() ?: 0.0

        val sum = number1 + number2

        textViewResult.text = "Result: $sum"
    }
}