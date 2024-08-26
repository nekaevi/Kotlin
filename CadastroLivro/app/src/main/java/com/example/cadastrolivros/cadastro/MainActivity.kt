package com.example.cadastrolivros.cadastro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrolivros.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nmliv = findViewById<EditText>(R.id.nmlivro)
        val nmAut = findViewById<EditText>(R.id.nmautor)
        val bntcad = findViewById<Button>(R.id.button)

        bntcad.setOnClickListener {
            val titulo = nmliv.text.toString()
            val autor = nmAut.text.toString()

            val intent = Intent(this, SegundaActivity::class.java)
            intent.putExtra("TITULO", titulo)
            intent.putExtra("AUTOR", autor)
            startActivity(intent)
        }



    }
}