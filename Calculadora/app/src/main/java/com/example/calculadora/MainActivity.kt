package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val Num1 = findViewById<EditText>(R.id.n1)
        val Num2 = findViewById<EditText>(R.id.n2)
        val btnSo = findViewById<Button>(R.id.button)
        val btnSu = findViewById<Button>(R.id.button2)
        val btnM = findViewById<Button>(R.id.button3)
        val btnD = findViewById<Button>(R.id.button4)
        val result = findViewById<TextView>(R.id.result)



        btnSo.setOnClickListener{
            val Num1 = Num1.text.toString().toInt()
            val Num2 = Num2.text.toString().toInt()
            val Soma = (Num1 + Num2)
            result.text = "O Resultado é $Soma"

        }
        btnSu.setOnClickListener{
            val Num1 = Num1.text.toString().toInt()
            val Num2 = Num2.text.toString().toInt()
            val Sub = (Num1 - Num2)
            result.text = "O Resultado é $Sub"
        }

        btnM.setOnClickListener{
            val Num1 = Num1.text.toString().toInt()
            val Num2 = Num2.text.toString().toInt()
            val Mult = (Num1 * Num2)
            result.text = "O Resultado é $Mult"
        }

        btnD.setOnClickListener {
            val num1 = Num1.text.toString().toIntOrNull() ?: 0
            val num2 = Num2.text.toString().toIntOrNull() ?: 1
            if (num2 != 0) {
                val div = num1 / num2
                result.text = "O Resultado é $div"
            } else {
                result.text = "Erro: Divisão por zero!"
            }
        }
    }
}


