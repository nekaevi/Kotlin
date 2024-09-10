package br.edu.fatecpg.listasfilmes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.listasfilmes.R
import br.edu.fatecpg.listasfilmes.dao.FavoritoDaoImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val dao = FavoritoDaoImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edtFilme = findViewById<EditText>(R.id.edt_filme)
        val edtDiretor = findViewById<EditText>(R.id.edt_diretor)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val fabLista = findViewById<FloatingActionButton>(R.id.fab_lista)

        btnSalvar.setOnClickListener{
            val filme = edtFilme.text.toString()
            val diretor = edtDiretor.text.toString()
            dao.adicionarFavorito(filme, diretor)
            Toast.makeText(this, "Filme Cadastrado", Toast.LENGTH_SHORT).show()
            edtFilme.text.clear()
            edtDiretor.text.clear()

        }
        fabLista.setOnClickListener {
            val intent = Intent(this,ListaActivity::class.java )
            startActivity(intent)
        }

    }
}