package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            val input = etNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Please enter a number"
                clearListView()
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null) {
                tvError.text = "Invalid input. Please enter a valid integer."
                clearListView()
                return@setOnClickListener
            } else if (n < 0) {
                tvError.text = "Negative numbers are not allowed. Please enter a positive integer."
                clearListView()
                return@setOnClickListener
            }

            tvError.text = ""
            val numbers = when {
                rbEven.isChecked -> getEvenNumbers(n)
                rbOdd.isChecked -> getOddNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).mapNotNull { k ->
            val sqrt = kotlin.math.sqrt(k.toDouble()).toInt()
            if (sqrt * sqrt == k) k else null
        }
    }

    private fun clearListView() {
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList<String>())
    }
}
