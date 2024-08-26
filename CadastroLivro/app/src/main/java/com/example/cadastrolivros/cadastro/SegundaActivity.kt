package com.example.cadastrolivros.cadastro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrolivros.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val tvAutor = findViewById<TextView>(R.id.tvAutor)
        val btn = findViewById<FloatingActionButton>(R.id.bntVoltar)

        val titulo = intent.getStringExtra("TITULO")
        val autor = intent.getStringExtra("AUTOR")

        tvTitulo.text = "Título: $titulo"
        tvAutor.text = "Autor: $autor"

        btn.setOnClickListener{
            finish()
        }

    }
}