package br.edu.fatepg.alunomatricula.views

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatepg.alunomatricula.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NomeAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nome_aluno)
        val fab = findViewById<FloatingActionButton>(R.id.fab_btn)
        val nome = findViewById<EditText>(R.id.nomeAluno)

        fab.setOnClickListener{
            val valNome = nome.text.toString()
            val intent = Intent(this, Matricula::class.java)
            intent.putExtra("NomeAluno", valNome)
            startActivity(intent)
        }
    }
}