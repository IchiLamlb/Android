package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var currentNumber: String = ""
    private var previousNumber: String = ""
    private var operator: String = ""
    private var result: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Khai báo các nút
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnEquals: Button = findViewById(R.id.btnEquals)

        val btnC: Button = findViewById(R.id.btnC)
        val btnCE: Button = findViewById(R.id.btnCE)
        val btnBS: Button = findViewById(R.id.btnBS)

        // Đăng ký sự kiện cho các nút số
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEach { button ->
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        // Đăng ký sự kiện cho các nút phép toán
        btnPlus.setOnClickListener { setOperator("+") }
        btnMinus.setOnClickListener { setOperator("-") }
        btnMultiply.setOnClickListener { setOperator("*") }
        btnDivide.setOnClickListener { setOperator("/") }

        // Đăng ký sự kiện cho nút bằng
        btnEquals.setOnClickListener { calculateResult() }

        // Đăng ký sự kiện cho nút xóa
        btnC.setOnClickListener { clearAll() }
        btnCE.setOnClickListener { clearCurrent() }
        btnBS.setOnClickListener { backspace() }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        textView.text = currentNumber
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            operator = op
            previousNumber = currentNumber
            currentNumber = ""
        }
    }

    private fun calculateResult() {
        if (currentNumber.isNotEmpty() && previousNumber.isNotEmpty()) {
            val num1 = previousNumber.toInt()
            val num2 = currentNumber.toInt()

            result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 != 0) {
                        num1 / num2
                    } else {
                        textView.text = "Cannot divided by 0"
                        return
                    }
                }

                else -> 0
            }

            textView.text = result.toString()
            currentNumber = result.toString()
            previousNumber = ""
            operator = ""
        }
    }

    private fun clearAll() {
        currentNumber = ""
        previousNumber = ""
        operator = ""
        result = 0
        textView.text = "0"
    }

    private fun clearCurrent() {
        currentNumber = ""
        textView.text = "0"
    }

    private fun backspace() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            textView.text = if (currentNumber.isEmpty()) "0" else currentNumber
        }
    }
}
