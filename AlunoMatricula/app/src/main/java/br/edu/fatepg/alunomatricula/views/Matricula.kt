package br.edu.fatepg.alunomatricula.views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatepg.alunomatricula.R
import kotlin.random.Random


class Matricula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matricula)

        val btn = findViewById<Button>(R.id.button)
        val numMat = IntArray(6) {Random.nextInt(0, 10)}
        val valNome = intent.getStringExtra("NomeAluno")

        val numMatriculaString = numMat.joinToString(separator = "")

        btn.setOnClickListener {
            Toast.makeText(this, "Nome do Aluno: $valNome", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Numero de matricula: $numMatriculaString", Toast.LENGTH_SHORT).show()

            numMat.forEach { numero ->
                Log.d("numero de matricula", numero.toString())
            }
        }
    }
}